package ua.hubanov.onlinestore_springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.hubanov.onlinestore_springboot.entity.Category;
import ua.hubanov.onlinestore_springboot.entity.Product;
import ua.hubanov.onlinestore_springboot.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public Set<Product> findByCategoryId(Long categoryId) throws Exception {
        Optional<Category> categoryFromBd = categoryRepository.findById(categoryId);
        Category category = categoryFromBd.orElse(new Category());
        Set<Product> productByCategoryId = category.getProducts();
        return productByCategoryId;

    }

//    public Object findByCategoryId(Long categoryId) {
//    }
}
