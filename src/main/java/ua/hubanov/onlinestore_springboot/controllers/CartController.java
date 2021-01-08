package ua.hubanov.onlinestore_springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.hubanov.onlinestore_springboot.entity.Product;
import ua.hubanov.onlinestore_springboot.entity.User;
import ua.hubanov.onlinestore_springboot.exceptions.StockIsNotEnoughException;
import ua.hubanov.onlinestore_springboot.service.CartService;
import ua.hubanov.onlinestore_springboot.service.OrderService;
import java.util.Map;

@Controller
public class CartController {
    final private OrderService orderService;
    final private CartService cartService;

    @Autowired
    public CartController(OrderService orderService, CartService cartService) {
        this.orderService = orderService;
        this.cartService = cartService;
    }

    @GetMapping("/user/cart")
    public String cart(@AuthenticationPrincipal User user, Model model) {
        Map<Product, Integer> productsInCart = cartService.getAllProductsInCart(user);
        model.addAttribute("products", productsInCart);
        model.addAttribute("totalPrice", cartService.getTotal(productsInCart));
        model.addAttribute("approvedOrderedProducts", orderService.getAllApprovedOrderedProductsOfUser(user));
        model.addAttribute("notApprovedOrderedProducts", orderService.getAllNotApprovedOrderedProductsOfUser(user));
        return "/user/cart";
    }

    //TODO make validation of neededQuantity without error_page
    @PostMapping("/user/cart")
    public String updateNeededQuantity(@AuthenticationPrincipal User user,
                                       @RequestParam(value = "productId") Long productId,
                                       @RequestParam(value = "neededQuantity") Integer neededQuantity,
                                       Model model) throws Exception {
        try {
            cartService.updateNeededQuantity(user, productId, neededQuantity);
        } catch (StockIsNotEnoughException e) {
            e.printStackTrace();
            model.addAttribute("errorString", e.getMessage());
            Map<Product, Integer> productsInCart = cartService.getAllProductsInCart(user);
            model.addAttribute("products", productsInCart);
            model.addAttribute("totalPrice", cartService.getTotal(productsInCart));
            model.addAttribute("approvedOrderedProducts", orderService.getAllApprovedOrderedProductsOfUser(user));
            model.addAttribute("notApprovedOrderedProducts", orderService.getAllNotApprovedOrderedProductsOfUser(user));
            return "/user/cart";
        }
        return "redirect:/user/cart";
    }

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

    //TODO think is StockIsNotEnough exception needed hear and rewrite
    @GetMapping("/user/cart/order")
    public String makeOrder(@AuthenticationPrincipal User user, Model model) {

        try {
            orderService.makeOrder(user);
        } catch (StockIsNotEnoughException e) {
            model.addAttribute("errorString", e.getMessage());
            Map<Product, Integer> productsInCart = cartService.getAllProductsInCart(user);
            model.addAttribute("products", productsInCart);
            model.addAttribute("totalPrice", cartService.getTotal(productsInCart));
            model.addAttribute("approvedOrderedProducts", orderService.getAllApprovedOrderedProductsOfUser(user));
            model.addAttribute("notApprovedOrderedProducts", orderService.getAllNotApprovedOrderedProductsOfUser(user));
            return "/user/cart";
        }

        return "redirect:/user/cart";
    }
}
