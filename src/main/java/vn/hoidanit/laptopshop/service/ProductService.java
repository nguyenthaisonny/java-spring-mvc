package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

<<<<<<< HEAD
import jakarta.servlet.http.HttpSession;
=======
>>>>>>> fd3a891263df14525f6d62a98ccb5a9e3d7047f7
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.CartDetailRepository;
import vn.hoidanit.laptopshop.repository.CartRepository;
import vn.hoidanit.laptopshop.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CartDetailRepository cartDetailRepository;
    private final CartRepository cartRepository;
    private final UserService userService;

    public ProductService(ProductRepository productRepository, CartRepository cartRepository,
            CartDetailRepository cartDetailRepository, UserService userService) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService = userService;
    }

    public Product handleSaveProduct(Product product) {
        return this.productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Product getProductById(long id) {
        return this.productRepository.findById(id);
    }

    public void handleDeleteProductById(long id) {
        this.productRepository.deleteById(id);
    }

<<<<<<< HEAD
    public void handleAddProductToCart(long productId, String email, HttpSession session) {
=======
    public void handleAddProductToCart(long productId, String email) {
>>>>>>> fd3a891263df14525f6d62a98ccb5a9e3d7047f7
        User user = this.userService.getUserByEmail(email);
        if (user == null) {
            return;
        }
        Cart cart = this.cartRepository.findByUser(user);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
<<<<<<< HEAD
            cart.setSum(0);
=======
            cart.setSum(1);
>>>>>>> fd3a891263df14525f6d62a98ccb5a9e3d7047f7
            cart = this.cartRepository.save(cart);
        }
        Product product = this.getProductById(productId);
        if (product == null) {
            return;
        }
<<<<<<< HEAD
        CartDetail currentCartDetail = this.cartDetailRepository.findByCartAndProduct(cart, product);
        if (currentCartDetail != null) {
            currentCartDetail.setQuantity(currentCartDetail.getQuantity() + 1);
            currentCartDetail.setPrice(currentCartDetail.getPrice() + product.getPrice());
            this.cartDetailRepository.save(currentCartDetail);
            product.setQuantity(product.getQuantity() - 1);
            this.productRepository.save(product);
        } else {
            CartDetail cartDetail = new CartDetail();
            cartDetail.setProduct(product);
            cart.setSum(cart.getSum() + 1);
            cartDetail.setCart(cart);
            cartDetail.setPrice(product.getPrice());
            cartDetail.setQuantity(1);
            this.cartRepository.save(cart);
            this.cartDetailRepository.save(cartDetail);
            product.setQuantity(product.getQuantity() - 1);
            this.productRepository.save(product);
        }
        session.setAttribute("sum", cart.getSum());
    }

    public Cart findCartByUser(User user) {
        return this.cartRepository.findByUser(user);
    }
=======
        CartDetail cartDetail = new CartDetail();
        cartDetail.setCart(cart);
        cartDetail.setProduct(product);
        cartDetail.setPrice(product.getPrice());
        cartDetail.setQuantity(1);
        this.cartDetailRepository.save(cartDetail);
        product.setQuantity(product.getQuantity() - 1);

        this.productRepository.save(product);
    }

>>>>>>> fd3a891263df14525f6d62a98ccb5a9e3d7047f7
}
