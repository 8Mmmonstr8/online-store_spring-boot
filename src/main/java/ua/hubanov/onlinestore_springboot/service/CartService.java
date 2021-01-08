package ua.hubanov.onlinestore_springboot.service;

import org.springframework.stereotype.Service;
import ua.hubanov.onlinestore_springboot.entity.Product;
import ua.hubanov.onlinestore_springboot.entity.User;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

public interface CartService {
    void addProductToCart(User user, Long productId) throws Exception;

    void removeProductFromCart(User user, Long productId) throws Exception;

    Map<Product, Integer> getAllProductsInCart(User user);

    BigDecimal getTotal(Map<Product, Integer> productsWithNeededQuantity);

    void clearProductsFromCart(User user);

    void updateNeededQuantity(User user, Long productId, Integer neededQuantity) throws Exception;
}
