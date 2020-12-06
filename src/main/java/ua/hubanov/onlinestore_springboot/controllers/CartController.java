package ua.hubanov.onlinestore_springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ua.hubanov.onlinestore_springboot.entity.User;
import ua.hubanov.onlinestore_springboot.service.CartService;
import ua.hubanov.onlinestore_springboot.service.ProductService;
import ua.hubanov.onlinestore_springboot.service.impl.CartServiceImpl;
import ua.hubanov.onlinestore_springboot.service.impl.ProductServiceImpl;

import java.security.Principal;

@Controller
public class CartController {
    final private ProductService productService;
    final private CartService cartService;

    @Autowired
    public CartController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @GetMapping("/user/cart")
    public String cart(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("products", user.getCart().getProducts());
        return "/user/cart";
    }

    @Transactional
    @GetMapping("/user/cart/addproduct/{productId}")
    public String addProductToCart(@PathVariable("productId") Long productId,
                                   @AuthenticationPrincipal User user, Model model) throws Exception {

        cartService.addProductToCart(user, productId);
        return "redirect:/user/";
    }

    @GetMapping("/user/cart/removeproduct/{productId}")
    public String removeProductToCart(@PathVariable("productId") Long productId,
                                   @AuthenticationPrincipal User user) throws Exception {

        cartService.removeProductFromCart(user, productId);
        return "redirect:/user/cart";
    }
}
