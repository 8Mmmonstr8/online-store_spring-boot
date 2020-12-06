package ua.hubanov.onlinestore_springboot.controllers;

import antlr.ASTNULLType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.hubanov.onlinestore_springboot.entity.User;
import ua.hubanov.onlinestore_springboot.repository.ProductRepository;
import ua.hubanov.onlinestore_springboot.repository.UserRepository;
import ua.hubanov.onlinestore_springboot.service.CartService;
import ua.hubanov.onlinestore_springboot.service.impl.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private ProductRepository productRepository;
    private CartService cartService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService,
                          ProductRepository productRepository, CartService cartService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.productRepository = productRepository;
        this.cartService = cartService;
    }

    @GetMapping("/")
    public String userHome(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("cartProducts", cartService.getAllProductsInCart(user));
        return "/user/user_home";
    }

//    @GetMapping("/temp-members-page")
//    public String temp(Model model) {
//        List<User> list = userRepository.findAll();
//        model.addAttribute("users", list);
//
//        return "user/temp_members_page";
//    }

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
        user.setPassword(new BCryptPasswordEncoder().encode(password));

        userRepository.save(user);
        return "redirect:/cart";
    }
}
