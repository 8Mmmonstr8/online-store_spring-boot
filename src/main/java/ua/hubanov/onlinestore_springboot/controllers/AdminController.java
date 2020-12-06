package ua.hubanov.onlinestore_springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.hubanov.onlinestore_springboot.entity.User;
import ua.hubanov.onlinestore_springboot.repository.CategoryRepository;
import ua.hubanov.onlinestore_springboot.repository.UserRepository;
import ua.hubanov.onlinestore_springboot.service.impl.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public AdminController(UserRepository userRepository, UserService userService, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("")
    public String adminHome() {
        return "/admin/admin_home";
    }

    @GetMapping("/users")
    public String index(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "/admin/users";
    }

    @GetMapping("/users/delete")
    public String removeUser(@RequestParam("userId") Long userId) throws Exception {
        Optional<User> userFromDB = userRepository.findById(userId);
        User user = userFromDB.orElseThrow(Exception::new);
        userRepository.delete(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/block")
    public String blockUser(@RequestParam("userId") Long userId) {
        Optional<User> userFromDB = userRepository.findById(userId);
        User user = new User();
        if (userFromDB.isPresent())
            user = userFromDB.get();
        user.setAccountLocked();
        userRepository.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/unblock")
    public String unBlockUser(@RequestParam("userId") Long userId) {
        Optional<User> userFromDB = userRepository.findById(userId);
        User user = new User();
        if (userFromDB.isPresent())
            user = userFromDB.get();
        user.setAccountUnLocked();
        userRepository.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/account")
    public String editOwnAccount(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "/admin/admin_account";
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
        return "redirect:/admin";
    }

}
