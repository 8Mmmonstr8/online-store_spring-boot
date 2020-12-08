package ua.hubanov.onlinestore_springboot.service;

import ua.hubanov.onlinestore_springboot.entity.OrderedProduct;
import ua.hubanov.onlinestore_springboot.entity.User;

import java.util.Set;

public interface OrderService {
    void makeOrder(User user);

    Set<OrderedProduct> getAllOrderedProducts(User user);

}
