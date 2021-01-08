package ua.hubanov.onlinestore_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hubanov.onlinestore_springboot.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByOrderByNameAsc();
    List<Product> findByOrderByNameDesc();
    List<Product> findByOrderByPriceAsc();
    List<Product> findByOrderByPriceDesc();
    List<Product> findByOrderByPublicationDateAsc();
    List<Product> findByOrderByPublicationDateDesc();
    List<Product> findAllByCategoryId(Long categoryId);

}
