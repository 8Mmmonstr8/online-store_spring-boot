package ua.hubanov.onlinestore_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hubanov.onlinestore_springboot.entity.Cart;
import ua.hubanov.onlinestore_springboot.entity.InCartProduct;
import ua.hubanov.onlinestore_springboot.entity.Product;

import java.util.Optional;
import java.util.Set;

public interface InCartProductRepository extends JpaRepository<InCartProduct, Long> {
    Set<InCartProduct> findAllByCart(Cart cart);
    void deleteAllByCart(Cart cart);
    void deleteByCartAndProductId(Cart cart, Long productId);
    Optional<InCartProduct> findByCartAndProductId(Cart cart, Long productId);
}
