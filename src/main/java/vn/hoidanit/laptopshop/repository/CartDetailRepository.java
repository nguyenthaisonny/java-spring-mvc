package vn.hoidanit.laptopshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Product;
=======
import vn.hoidanit.laptopshop.domain.CartDetail;
>>>>>>> fd3a891263df14525f6d62a98ccb5a9e3d7047f7
import vn.hoidanit.laptopshop.domain.User;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
<<<<<<< HEAD
    CartDetail findByCartAndProduct(Cart cart, Product product);
=======

>>>>>>> fd3a891263df14525f6d62a98ccb5a9e3d7047f7
}
