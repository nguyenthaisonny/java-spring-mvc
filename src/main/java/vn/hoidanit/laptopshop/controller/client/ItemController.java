package vn.hoidanit.laptopshop.controller.client;

<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> fd3a891263df14525f6d62a98ccb5a9e3d7047f7
import org.springframework.boot.autoconfigure.jms.JmsProperties.Listener.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
<<<<<<< HEAD
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
=======
>>>>>>> fd3a891263df14525f6d62a98ccb5a9e3d7047f7
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ItemController {
    private final ProductService productService;

    public ItemController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public String getProductDetailPage(Model model, @PathVariable long id) {
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("id", id);
        return "client/product/detail";
    }

    @PostMapping("/add-product-to-cart/{id}")
    public String addProductToCart(@PathVariable long id, HttpServletRequest request) {
        // TODO: process POST request
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

<<<<<<< HEAD
        this.productService.handleAddProductToCart(id, email, session);
=======
        this.productService.handleAddProductToCart(id, email);
>>>>>>> fd3a891263df14525f6d62a98ccb5a9e3d7047f7

        return "redirect:/";
    }

<<<<<<< HEAD
    @GetMapping("/cart")
    public String getCartPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        long userId = (long) session.getAttribute("id");
        User user = new User();
        user.setId(userId);
        Cart cart = this.productService.findCartByUser(user);
        double totalPrice = 0;
        if (cart != null) {
            List<CartDetail> cartDetails = cart.getCartDetails();
            for (CartDetail cartDetail : cartDetails) {
                totalPrice += cartDetail.getProduct().getPrice() * cartDetail.getQuantity();
            }
            model.addAttribute("cartDetails", cartDetails);
            model.addAttribute("totalPrice", totalPrice);
        }
        return "client/cart/show";
    }

=======
>>>>>>> fd3a891263df14525f6d62a98ccb5a9e3d7047f7
}
