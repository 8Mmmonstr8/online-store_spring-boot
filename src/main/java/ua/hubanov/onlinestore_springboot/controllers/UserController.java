package ua.hubanov.onlinestore_springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.hubanov.onlinestore_springboot.entity.User;
import ua.hubanov.onlinestore_springboot.repository.ProductRepository;
import ua.hubanov.onlinestore_springboot.service.CartService;
import ua.hubanov.onlinestore_springboot.service.impl.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ProductRepository productService;
    private final CartService cartService;

    @Autowired
    public UserController(UserService userService, ProductRepository productService,
                          CartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping("/")
    public String userHome(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("cartProducts", cartService.getAllProductsInCart(user));
        return "/user/user_home";
    }

    @GetMapping("/account")
    public String editOwnAccount(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "/user/user_account";
    }

    // TODO: 1. Make validation of fields. 2. Adjust Method
    @PostMapping("/account/{userId}")
    public String updateUserInfo(@AuthenticationPrincipal User user,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String password) {
//        if (bindingResult.hasErrors())
//            return "user/user_account";
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);

        userService.saveUser(user);
        return "redirect:/user/cart";
    }
}
