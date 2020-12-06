package ua.hubanov.onlinestore_springboot.service;

import org.springframework.stereotype.Service;
import ua.hubanov.onlinestore_springboot.entity.Category;
import ua.hubanov.onlinestore_springboot.entity.Product;

import java.util.Optional;
import java.util.Set;

public interface ProductService {

    Set<Product> findByCategoryId(Long categoryId) throws Exception;

    Optional<Product> findProductById(Long id);
}
