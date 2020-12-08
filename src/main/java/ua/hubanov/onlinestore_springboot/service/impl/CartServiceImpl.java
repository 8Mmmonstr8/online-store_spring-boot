package ua.hubanov.onlinestore_springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.hubanov.onlinestore_springboot.entity.Product;
import ua.hubanov.onlinestore_springboot.entity.User;
import ua.hubanov.onlinestore_springboot.repository.CartRepository;
import ua.hubanov.onlinestore_springboot.repository.ProductRepository;
import ua.hubanov.onlinestore_springboot.service.CartService;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    final private ProductRepository productRepository;
    final private UserService userService;
    final private ua.hubanov.onlinestore_springboot.service.ProductService productService;
    final private CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(ProductRepository productRepository, UserService userService,
                           ua.hubanov.onlinestore_springboot.service.ProductService productService, CartRepository cartRepository) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.productService = productService;
        this.cartRepository = cartRepository;
    }

    @Override
    public void removeProductFromCart(User user, Long productId) throws Exception {
//        user.getCart().setProducts(user.getCart().getProducts().stream().filter(a -> (!a.getId().equals(productId)) ).collect(Collectors.toSet()));
//        cartRepository.save(user.getCart());
    }

    // TODO Remvoe method
    @Override
    public Map<Product, Integer> getProductsInCart() {
        return null;
    }

    @Override
    public void addProductToCart(User user, Long productId) throws Exception {
        Product product = productService.findProductById(productId).orElseThrow(Exception::new);
        Set<Product> products = user.getCart().getProducts();
        products.add(product);
        cartRepository.save(user.getCart());
    }

    @Override
    public Map<Product, Integer> getAllProductsInCart(User user) {
        return user.getCart().getProducts().stream().collect(Collectors.toMap(product -> product, productQuantity -> 1));
        //return user.getCart().getProducts();
    }

    @Override
    public BigDecimal getTotal(Map<Product, Integer> productsWithNeededQuantity) {
        return productsWithNeededQuantity.entrySet().stream()
                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public void clearProductsFromCart(User user) {
        user.getCart().setProducts(new HashSet<>());
        cartRepository.save(user.getCart());
    }
}
