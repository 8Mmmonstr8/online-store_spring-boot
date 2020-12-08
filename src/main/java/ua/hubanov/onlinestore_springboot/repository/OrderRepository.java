package ua.hubanov.onlinestore_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.hubanov.onlinestore_springboot.entity.Order;
import ua.hubanov.onlinestore_springboot.entity.User;

import java.util.Optional;
import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Set<Order> findAllByUser(User user);

    Set<Order> findAllByIsApprovedIsFalse();
    Set<Order> findAllByIsApprovedIsTrue();

    Optional<Order> findOneById(Long orderId);

}
