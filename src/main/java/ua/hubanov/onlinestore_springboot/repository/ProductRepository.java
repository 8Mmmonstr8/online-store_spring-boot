package ua.hubanov.onlinestore_springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.hubanov.onlinestore_springboot.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategoryId(Long categoryId);

    Page<Product> findAllByCategoryIdOrderByNameAsc(Long categoryId, Pageable pageable);
    Page<Product> findAllByCategoryIdOrderByNameDesc(Long categoryId, Pageable pageable);
    Page<Product> findAllByCategoryIdOrderByPriceAsc(Long categoryId, Pageable pageable);
    Page<Product> findAllByCategoryIdOrderByPriceDesc(Long categoryId, Pageable pageable);
    Page<Product> findAllByCategoryIdOrderByPublicationDateAsc(Long categoryId, Pageable pageable);
    Page<Product> findAllByCategoryIdOrderByPublicationDateDesc(Long categoryId, Pageable pageable);

    Page<Product> findAllByOrderByNameAsc(Pageable pageable);
    Page<Product> findAllByOrderByNameDesc(Pageable pageable);
    Page<Product> findAllByOrderByPriceAsc(Pageable pageable);
    Page<Product> findAllByOrderByPriceDesc(Pageable pageable);
    Page<Product> findAllByOrderByPublicationDateAsc(Pageable pageable);
    Page<Product> findAllByOrderByPublicationDateDesc(Pageable pageable);

}
