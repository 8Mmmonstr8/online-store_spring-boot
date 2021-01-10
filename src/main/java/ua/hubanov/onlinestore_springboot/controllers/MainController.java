package ua.hubanov.onlinestore_springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.hubanov.onlinestore_springboot.controllers.util.PageWrapper;
import ua.hubanov.onlinestore_springboot.entity.Product;
import ua.hubanov.onlinestore_springboot.entity.User;
import ua.hubanov.onlinestore_springboot.service.ProductService;
import ua.hubanov.onlinestore_springboot.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

@Controller
public class MainController {
    private final UserServiceImpl userServiceImpl;
    private final ProductService productService;

    @Autowired
    public MainController(UserServiceImpl userServiceImpl, ProductService productService) {
        this.userServiceImpl = userServiceImpl;
        this.productService = productService;
    }
/*
    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) throws Exception {
        String category = request.getParameter("category");
        String sortBy = request.getParameter("sortBy");
        if (sortBy == null) {
            sortBy = "nameAsc";
        }
        List<Product> products;
        if (category == null || category.equals("all")) {
            products = productService.findAll();
            products = productService.sortProductsBy(products, sortBy);
            category = "all";
        } else {
            Long categoryId = Long.valueOf(category);
            products = productService.findAllByCategoryId(categoryId);
            products = productService.sortProductsBy(products, sortBy);
        }
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        model.addAttribute("sortBy", sortBy);

        return "index";
    }
    */

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model, Pageable pageable) throws Exception {
        String category = request.getParameter("category");
        String sortBy = request.getParameter("sortBy");
        if (sortBy == null)
            sortBy = "nameAsc";

        if (category == null || category.equals("all"))
            category = "0";


        Page<Product> productPage = productService.findAllByCategoryIdAndSorted(Long.parseLong(category), sortBy, pageable);
        PageWrapper<Product> page = new PageWrapper<Product>(productPage, "/");

        model.addAttribute("products", page.getContent());
        model.addAttribute("category", category);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("page", page);


//        Page<Note> notePage = filterAndSort(pageable);
//        PageWrapper<Note> page = new PageWrapper<Note>(notePage, "/");
//        model.addAttribute("notes", page.getContent());
//        model.addAttribute("sort", sortDateMethod);
//        model.addAttribute("filter", filterMethod);
//        model.addAttribute("page", page);
//        return "index";


        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @GetMapping("/registration")
    public String newPerson(@ModelAttribute("user") User user) {
        return "registration";
    }

    @PostMapping("/registration")
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "registration";

        if (!userServiceImpl.saveNewUser(user)) {
            model.addAttribute("userEmailError", "Пользователь с таким Email уже существует");
            return "registration";
        }
        return "redirect:/login";
    }
}
