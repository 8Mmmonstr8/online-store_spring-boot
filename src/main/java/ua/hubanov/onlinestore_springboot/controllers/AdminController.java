package ua.hubanov.onlinestore_springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.hubanov.onlinestore_springboot.repository.UserRepository;
import ua.hubanov.onlinestore_springboot.service.UserService;

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
}
