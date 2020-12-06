package ua.hubanov.onlinestore_springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.hubanov.onlinestore_springboot.entity.Product;
import ua.hubanov.onlinestore_springboot.entity.User;
import ua.hubanov.onlinestore_springboot.repository.CategoryRepository;
import ua.hubanov.onlinestore_springboot.repository.ProductRepository;
import ua.hubanov.onlinestore_springboot.repository.UserRepository;
import ua.hubanov.onlinestore_springboot.service.CartService;
import ua.hubanov.onlinestore_springboot.service.impl.ProductServiceImpl;
import ua.hubanov.onlinestore_springboot.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

@Controller
public class MainController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductServiceImpl productService;
    private CartService cartService;

    @Autowired
    public MainController(UserRepository userRepository, UserService userService,
                          ProductRepository productRepository, CategoryRepository categoryRepository,
                          ProductServiceImpl productService, CartService cartService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productService = productService;
        this.cartService = cartService;
    }

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) throws Exception {

        String category = request.getParameter("category");
        if (category == null || category.equals("all")) {
            model.addAttribute("products", productRepository.findAll());
//            model.addAttribute("cartProducts", cartService.getAllProductsInCart(user));
        }
        else {
            Long categoryId = Long.valueOf(category);
            model.addAttribute("products", productService.findByCategoryId(categoryId));
        }
        return "index";
    }
//    departmentRepo.findById(departmentId).employees

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

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("title", "Cart");
        return "cart";
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

        if (!userService.saveUser(user)) {
            model.addAttribute("userEmailError", "Пользователь с таким Email уже существует");
            return "registration";
        }
//        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/tools/sortbynameasc")
    public String sortByNameAsc(HttpServletRequest request, Model model) throws Exception {
        String category = request.getParameter("category");
        if (category == null || category.equals("all"))
            model.addAttribute("products", productRepository.findByOrderByNameAsc());
        else {
            Long categoryId = Long.valueOf(category);
            Set<Product> productSet = productService.findByCategoryId(categoryId);
            List<Product> productList = new ArrayList<>(productSet);
            productList.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));

//            Set<Product> sortedProductSet = productSet.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
            model.addAttribute("products", productList);
        }


//
//        model.addAttribute("products", productRepository.findByOrderByNameAsc());
        return "index";
    }

    @GetMapping("/tools/sortbynamedesc")
    public String sortByNameDesc(Model model) {
        model.addAttribute("products", productRepository.findByOrderByNameDesc());
        return "index";
    }

    @GetMapping("/tools/sortbypriceasc")
    public String sortByPriceAsc(Model model) {
        model.addAttribute("products", productRepository.findByOrderByPriceAsc());
        return "index";
    }

    @GetMapping("/tools/sortbypricedesc")
    public String sortByPriceDesc(Model model) {
        model.addAttribute("products", productRepository.findByOrderByPriceDesc());
        return "index";
    }

    @GetMapping("/tools/sortbypublicationdateasc")
    public String sortByPublicationDateAsc(Model model) {
        model.addAttribute("products", productRepository.findByOrderByPublicationDateAsc());
        return "index";
    }

    @GetMapping("/tools/sortbypublicationdatedesc")
    public String sortByPublicationDateDesc(Model model) {
        model.addAttribute("products", productRepository.findByOrderByPublicationDateDesc());
        return "index";
    }
}
