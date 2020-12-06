package ua.hubanov.onlinestore_springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.hubanov.onlinestore_springboot.entity.Cart;
import ua.hubanov.onlinestore_springboot.entity.Product;
import ua.hubanov.onlinestore_springboot.entity.User;
import ua.hubanov.onlinestore_springboot.repository.CartRepository;
import ua.hubanov.onlinestore_springboot.repository.ProductRepository;
import ua.hubanov.onlinestore_springboot.service.CartService;
import ua.hubanov.onlinestore_springboot.service.ProductService;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    final private ProductRepository productRepository;
    final private UserService userService;
    final private ProductService productService;
    final private CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(ProductRepository productRepository, UserService userService,
                           ProductService productService, CartRepository cartRepository) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.productService = productService;
        this.cartRepository = cartRepository;
    }

    @Override
    public void removeProductFromCart(User user, Long productId) throws Exception {
        user.getCart().setProducts(user.getCart().getProducts().stream().filter(a -> (!a.getId().equals(productId)) ).collect(Collectors.toSet()));
        cartRepository.save(user.getCart());
    }


    @Override
    public Map<Product, Integer> getProductsInCart() {
        return null;
    }

    @Override
    public BigDecimal getTotal() {
        return null;
    }

    @Override
    public void addProductToCart(User user, Long productId) throws Exception {
        Product product = productService.findProductById(productId).orElseThrow(Exception::new);
        Cart cart = user.getCart();
        Set<Product> products = cart.getProducts();
        products.add(product);
        user.getCart().setProducts(products);
        cartRepository.save(user.getCart());
    }

    @Override
    public Set<Product> getAllProductsInCart(User user) {
        return user.getCart().getProducts();
    }
}
