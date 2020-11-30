package ua.hubanov.onlinestore_springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.hubanov.onlinestore_springboot.entity.User;
import ua.hubanov.onlinestore_springboot.repository.UserRepository;
import ua.hubanov.onlinestore_springboot.service.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public AdminController(UserRepository userRepository, UserService userService) {
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

    @PostMapping("/users/{user}/remove")
    public String removeUser(@PathVariable User user) {
        userRepository.delete(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/users/{user}/block")
    public String blockUser(@PathVariable User user) {
        user.setAccountLocked();
        userRepository.save(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/users/{user}/unblock")
    public String unBlockUser(@PathVariable User user) {
        user.setAccountUnLocked();
        userRepository.save(user);
        return "redirect:/admin/users";
    }
}
