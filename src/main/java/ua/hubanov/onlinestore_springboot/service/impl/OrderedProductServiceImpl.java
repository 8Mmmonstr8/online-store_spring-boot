package ua.hubanov.onlinestore_springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.hubanov.onlinestore_springboot.entity.OrderedProduct;
import ua.hubanov.onlinestore_springboot.repository.OrderedProductRepository;
import ua.hubanov.onlinestore_springboot.service.OrderedProductService;

@Service
public class OrderedProductServiceImpl implements OrderedProductService {
    private final OrderedProductRepository orderedProductRepository;

    @Autowired
    public OrderedProductServiceImpl(OrderedProductRepository orderedProductRepository) {
        this.orderedProductRepository = orderedProductRepository;
    }

    @Override
    public void save(OrderedProduct orderedProduct) {
        orderedProductRepository.save(orderedProduct);
    }
}
