package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UploadService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller()
@RequestMapping("/admin/product")
public class ProductController {
    private final UploadService uploadService;
    private final ProductService productService;

    public ProductController(UploadService uploadService, ProductService productService) {
        this.uploadService = uploadService;
        this.productService = productService;
    }

    @GetMapping
    public String getProductPage(Model model) {
        List<Product> products = this.productService.getAllProducts();
        model.addAttribute("products", products);
        return "admin/product/show";
    }

    @GetMapping("/create")
    public String getCreateProductPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute("newProduct") @Valid Product product,
            BindingResult newProductBindingResult,
            @RequestParam("productFile") MultipartFile file) {
        List<FieldError> errors = newProductBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(error.getField() + " - " + error.getDefaultMessage());
        }
        if (newProductBindingResult.hasErrors()) {
            return "/admin/product/create";
        }
        String productImage = this.uploadService.handleSaveUploadFile(file,
                "product");
        product.setImage(productImage);
        this.productService.handleSaveProduct(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/update/{id}")
    public String getUpdateProductPage(Model model, @PathVariable long id) {
        Product newProduct = this.productService.getProductById(id);
        if (newProduct == null)
            System.out.println("This product is not exist");
        model.addAttribute("image", newProduct.getImage());

        model.addAttribute("newProduct", newProduct);
        model.addAttribute("id", id);
        return "/admin/product/update";
    }

    @PostMapping("/update")
    public String updateProduct(
            Model model,
            @ModelAttribute("newProduct") @Valid Product product,
            BindingResult newProductBindingResult,
            @RequestParam("productFile") MultipartFile file

    ) {
        List<FieldError> errors = newProductBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(error.getField() + " - " + error.getDefaultMessage());
        }
        if (newProductBindingResult.hasErrors()) {
            model.addAttribute("image", product.getImage());
            return "/admin/product/update";
        }
        Product productDb = this.productService.getProductById(product.getId());
        if (productDb == null)
            System.out.print("This product doesnot exist");
        if (product.getPriceToString() == "") {
            product.setPrice(0);
        }
        if (product.getQuantityToString() == "") {
            product.setQuantity(1);
        }
        productDb.setId(product.getId());
        productDb.setName(product.getName());
        productDb.setPrice(product.getPrice());
        productDb.setFactory(product.getFactory());
        productDb.setTarget(product.getTarget());
        productDb.setDetailDesc(product.getDetailDesc());
        productDb.setShortDesc(product.getShortDesc());
        productDb.setQuantity(product.getQuantity());
        if (!file.isEmpty()) {
            String productImage = this.uploadService.handleSaveUploadFile(file,
                    "product");
            productDb.setImage(productImage);
        }
        this.productService.handleSaveProduct(productDb);

        return "redirect:/admin/product";
    }

    @GetMapping("/delete/{id}")
    public String getDeleteProductPage(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        return "/admin/product/delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
        this.productService.handleDeleteProductById(id);

        return "redirect:/admin/product";

    }

    @GetMapping("/{id}")
    public String getProductDetailPage(Model model, @PathVariable long id) {
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("id", id);
        return "admin/product/detail";
    }

}
