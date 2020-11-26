package ua.hubanov.onlinestore_springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.hubanov.onlinestore_springboot.entity.User;
import ua.hubanov.onlinestore_springboot.repository.UserRepository;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String home() {
        return "user/home";
    }

    @GetMapping("/temp-members-page")
    public String temp(Model model) {
        List<User> list = userRepository.findAll();
        model.addAttribute("users", list);

        return "user/temp_members_page";
    }

    @GetMapping("/registration")
    public String newPerson(@ModelAttribute("user") User user) {
        return "user/registration";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {

        userRepository.save(user);
        return "redirect:/user/";
    }

}
