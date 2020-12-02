package ua.hubanov.onlinestore_springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.hubanov.onlinestore_springboot.entity.Category;
import ua.hubanov.onlinestore_springboot.entity.Product;
import ua.hubanov.onlinestore_springboot.repository.CategoryRepository;
import ua.hubanov.onlinestore_springboot.repository.ProductRepository;

@Controller
@RequestMapping("/admin/products")
public class ProductController {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("")
    public String productsMain(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "/admin/products";
    }

    @GetMapping("/categories")
    public String categoriesMain(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "/admin/categories";
    }

    @GetMapping("/categories/cat-form")
    public String newCategory(@ModelAttribute("category") Category category) {
        return "/admin/category_form";
    }

    @PostMapping("/categories/cat-form")
    public String create(@ModelAttribute("category") Category category, Model model) {
        categoryRepository.save(category);
        return "redirect:/admin/products/categories";
    }

    @GetMapping("/product-form")
    public String productForm(@ModelAttribute("product") Product product, Model model) {
        model.addAttribute("productCategories", categoryRepository.findAll());
        return "/admin/product_form";
    }

    @PostMapping("/product-form")
    public String createProduct(@ModelAttribute("product") Product product) {
        productRepository.save(product);
        return "redirect:/admin/products";
    }
}
