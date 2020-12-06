package ua.hubanov.onlinestore_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hubanov.onlinestore_springboot.entity.Cart;
import ua.hubanov.onlinestore_springboot.entity.Category;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
