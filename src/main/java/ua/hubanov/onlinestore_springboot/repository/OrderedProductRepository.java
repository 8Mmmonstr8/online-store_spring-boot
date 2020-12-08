package ua.hubanov.onlinestore_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.hubanov.onlinestore_springboot.entity.OrderedProduct;

@Repository
public interface OrderedProductRepository extends JpaRepository<OrderedProduct, Long> {

}
