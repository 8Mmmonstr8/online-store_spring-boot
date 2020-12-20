package ua.hubanov.onlinestore_springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hubanov.onlinestore_springboot.entity.Cart;
import ua.hubanov.onlinestore_springboot.entity.InCartProduct;
import ua.hubanov.onlinestore_springboot.entity.Product;
import ua.hubanov.onlinestore_springboot.entity.User;
import ua.hubanov.onlinestore_springboot.exceptions.StockIsNotEnoughException;
import ua.hubanov.onlinestore_springboot.repository.CartRepository;
import ua.hubanov.onlinestore_springboot.repository.InCartProductRepository;
import ua.hubanov.onlinestore_springboot.repository.ProductRepository;
import ua.hubanov.onlinestore_springboot.service.CartService;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    final private ProductRepository productRepository;
    final private UserService userService;
    final private ua.hubanov.onlinestore_springboot.service.ProductService productService;
    final private CartRepository cartRepository;
    final private InCartProductRepository inCartProductRepository;

    @Autowired
    public CartServiceImpl(ProductRepository productRepository, UserService userService,
                           ua.hubanov.onlinestore_springboot.service.ProductService productService,
                           CartRepository cartRepository, InCartProductRepository inCartProductRepository) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.productService = productService;
        this.cartRepository = cartRepository;
        this.inCartProductRepository = inCartProductRepository;
    }

    // TODO remake method
    @Transactional
    @Override
    public void removeProductFromCart(User user, Long productId) throws Exception {
        inCartProductRepository.deleteByCartAndProductId(user.getCart(), productId);

//        user.getCart().setProducts(user.getCart().getProducts().stream().filter(a -> (!a.getId().equals(productId)) ).collect(Collectors.toSet()));
//        cartRepository.save(user.getCart());
    }

    // TODO Remvoe method
    @Override
    public Map<Product, Integer> getProductsInCart() {
        return null;
    }

    // TODO make new Exception for this method and adding quantity
    @Override
    public void addProductToCart(User user, Long productId) throws Exception {
        Product product = productService.findProductById(productId).orElseThrow(Exception::new);

        Cart cart = cartRepository.findById(user.getCart().getId()).orElseThrow(Exception::new);

        Optional<InCartProduct> optionalInCartProduct = inCartProductRepository.findByCartAndProductId(user.getCart(), productId);
        if (optionalInCartProduct.isPresent())
            System.out.println("product already in cart");
        else {
            InCartProduct inCartProduct = new InCartProduct();
            inCartProduct.setProduct(product);
            inCartProduct.setCart(cart);
            inCartProductRepository.save(inCartProduct);
        }
    }

    @Override
    public Map<Product, Integer> getAllProductsInCart(User user) {
//        Set<InCartProduct> inCartProducts = inCartProductRepository.findAllByCart(user.getCart());
        return inCartProductRepository.findAllByCart(user.getCart()).stream()
                .collect(Collectors.toMap(inCartProduct -> inCartProduct.getProduct(), productQuantity -> productQuantity.getNeededQuantity()));
        //        return user.getCart().getProducts().stream().collect(Collectors.toMap(product -> product, productQuantity -> 1));
        //return user.getCart().getProducts();
    }

    @Override
    public BigDecimal getTotal(Map<Product, Integer> productsWithNeededQuantity) {
        return productsWithNeededQuantity.entrySet().stream()
                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    @Transactional
    @Override
    public void clearProductsFromCart(User user) {
        inCartProductRepository.deleteAllByCart(user.getCart());

//        user.getCart().setProducts(new HashSet<>());
//        cartRepository.save(user.getCart());
    }

    //TODO make new Exception (Product has not been founded in cart)
    @Override
    public void updateNeededQuantity(User user, Long productId, Integer neededQuantity) throws Exception {
        InCartProduct inCartProduct = inCartProductRepository
                .findByCartAndProductId(user.getCart(), productId).orElseThrow(Exception::new);
        Product product = productRepository.getOne(productId);
        if (neededQuantity <= product.getQuantity()) {
            inCartProduct.setNeededQuantity(neededQuantity);
            inCartProductRepository.save(inCartProduct);
        } else {
            throw new StockIsNotEnoughException();
        }
    }
}
