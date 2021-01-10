package ua.hubanov.onlinestore_springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.hubanov.onlinestore_springboot.entity.Category;
import ua.hubanov.onlinestore_springboot.entity.Product;
import ua.hubanov.onlinestore_springboot.repository.CategoryRepository;
import ua.hubanov.onlinestore_springboot.repository.ProductRepository;

import java.util.ArrayList;
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
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllByCategoryId(Long categoryId) {
//        Optional<Category> categoryFromBd = categoryRepository.findById(categoryId);
//        Category category = categoryFromBd.orElse(new Category());
//        Set<Product> productByCategoryId = category.getProducts();
        List<Product> products = productRepository.findAllByCategoryId(categoryId);
        return products;

    }

    @Override
    public List<Product> sortProductsBy(List<Product> products, String sortBy) {
        switch (sortBy) {
            case "nameAsc": products.sort((o1, o2) -> o1.getName().compareTo(o2.getName())); return products;
            case "nameDesc": products.sort((o1, o2) -> o2.getName().compareTo(o1.getName())); return products;
            case "priceAsc": products.sort((o1, o2) -> o1.getPrice().compareTo(o2.getPrice())); return products;
            case "priceDesc": products.sort((o1, o2) -> o2.getPrice().compareTo(o1.getPrice())); return products;
            case "pubDateAsc": products.sort((o1, o2) -> o1.getPublicationDate().compareTo(o2.getPublicationDate())); return products;
            case "pubDateDesc": products.sort((o1, o2) -> o2.getPublicationDate().compareTo(o1.getPublicationDate())); return products;
            default: return products;
        }
    }

    @Override
    public Page<Product> findAllByCategoryIdAndSorted(Long categoryId, String sortBy, Pageable pageable) {
        if (categoryId == 0L) {
            switch (sortBy) {
                case "nameAsc": return productRepository.findAllByOrderByNameAsc(pageable);
                case "nameDesc": return productRepository.findAllByOrderByNameDesc(pageable);
                case "priceAsc": return productRepository.findAllByOrderByPriceAsc(pageable);
                case "priceDesc": return productRepository.findAllByOrderByPriceDesc(pageable);
                case "pubDateAsc": return productRepository.findAllByOrderByPublicationDateAsc(pageable);
                case "pubDateDesc": return productRepository.findAllByOrderByPublicationDateDesc(pageable);
                default: return productRepository.findAll(pageable);
            }
        } else {
            switch (sortBy) {
                case "nameAsc": return productRepository.findAllByCategoryIdOrderByNameAsc(categoryId, pageable);
                case "nameDesc": return productRepository.findAllByCategoryIdOrderByNameDesc(categoryId, pageable);
                case "priceAsc": return productRepository.findAllByCategoryIdOrderByPriceAsc(categoryId, pageable);
                case "priceDesc": return productRepository.findAllByCategoryIdOrderByPriceDesc(categoryId, pageable);
                case "pubDateAsc": return productRepository.findAllByCategoryIdOrderByPublicationDateAsc(categoryId, pageable);
                case "pubDateDesc": return productRepository.findAllByCategoryIdOrderByPublicationDateDesc(categoryId, pageable);
                default: return productRepository.findAll(pageable);
            }
        }
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
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
