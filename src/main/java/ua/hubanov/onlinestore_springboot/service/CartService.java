package ua.hubanov.onlinestore_springboot.service;

import org.springframework.stereotype.Service;
import ua.hubanov.onlinestore_springboot.entity.Product;
import ua.hubanov.onlinestore_springboot.entity.User;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface CartService {
    void addProductToCart(User user, Long productId) throws Exception;

    void removeProductFromCart(User user, Long productId) throws Exception;

    Map<Product, Integer> getProductsInCart();

 //   void checkout() throws NotEnoughProductsInStockException;

    BigDecimal getTotal();


    Set<Product> getAllProductsInCart(User user);
}
