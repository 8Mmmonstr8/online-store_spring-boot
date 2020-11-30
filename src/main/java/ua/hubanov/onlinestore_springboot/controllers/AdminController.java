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

    @GetMapping("/users/{user}")
    public String userEditForm(@PathVariable User user, Model model) {
//        Optional<User> userFromBd = userRepository.findById(id);
//        //todo
//        User user = userFromBd.get();
        model.addAttribute("user", user);
        String s = String.valueOf(user.isAccountNonLocked());
        model.addAttribute("isActive", s);
        return "/admin/user_edit_form";
    }

    @PostMapping("/users")
    public String userUpdate(
            @RequestParam boolean active,
            @RequestParam String firstName,
            @RequestParam("id") User user) {

        if (active == false)
            user.setAccountLocked();

        userRepository.save(user);

        return "redirect:/admin/users";
    }
}
