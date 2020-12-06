package ua.hubanov.onlinestore_springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.hubanov.onlinestore_springboot.entity.Category;
import ua.hubanov.onlinestore_springboot.entity.Product;
import ua.hubanov.onlinestore_springboot.repository.CategoryRepository;
import ua.hubanov.onlinestore_springboot.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ua.hubanov.onlinestore_springboot.service.ProductService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Set<Product> findByCategoryId(Long categoryId) throws Exception {
//        Optional<Category> categoryFromBd = categoryRepository.findById(categoryId);
//        Category category = categoryFromBd.orElse(new Category());
//        Set<Product> productByCategoryId = category.getProducts();
        return null;

    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

//    public Object findByCategoryId(Long categoryId) {
//    }


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }
}
