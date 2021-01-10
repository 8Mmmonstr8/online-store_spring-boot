package ua.hubanov.onlinestore_springboot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.hubanov.onlinestore_springboot.entity.Category;
import ua.hubanov.onlinestore_springboot.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductService {

    List<Product> findAllByCategoryId(Long categoryId);
    Optional<Product> findProductById(Long id);
    List<Product> findAll();
    void saveProduct(Product product);
    List<Category> findAllCategory();
    void saveCategory(Category category);
    List<Product> sortProductsBy(List<Product> products, String sortBy);

    Page<Product> findAllByCategoryIdAndSorted(Long categoryId, String sortBy, Pageable pageable);
}
