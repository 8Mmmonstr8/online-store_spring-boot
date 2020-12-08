package ua.hubanov.onlinestore_springboot.service;

import ua.hubanov.onlinestore_springboot.entity.OrderedProduct;

public interface OrderedProductService {
    void save(OrderedProduct orderedProduct);
}
