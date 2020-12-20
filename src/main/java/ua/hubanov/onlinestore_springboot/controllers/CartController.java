package ua.hubanov.onlinestore_springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.hubanov.onlinestore_springboot.entity.User;
import ua.hubanov.onlinestore_springboot.exceptions.StockIsNotEnoughException;
import ua.hubanov.onlinestore_springboot.service.CartService;
import ua.hubanov.onlinestore_springboot.service.OrderService;

import javax.validation.Valid;

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
        model.addAttribute("products", cartService.getAllProductsInCart(user));
        model.addAttribute("totalPrice", cartService.getTotal(cartService.getAllProductsInCart(user)));
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
            return "error_page";
        }
        return "redirect:/user/cart";
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
