package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
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

    public void handleAddProductToCart(long productId, String email, HttpSession session) {
        User user = this.userService.getUserByEmail(email);
        if (user == null) {
            return;
        }
        Cart cart = this.cartRepository.findByUser(user);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart.setSum(0);
            cart = this.cartRepository.save(cart);
        }
        Product product = this.getProductById(productId);
        if (product == null) {
            return;
        }
        CartDetail currentCartDetail = this.cartDetailRepository.findByCartAndProduct(cart, product);
        if (currentCartDetail != null) {
            currentCartDetail.setQuantity(currentCartDetail.getQuantity() + 1);
            currentCartDetail.setPrice(currentCartDetail.getPrice());
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
}
