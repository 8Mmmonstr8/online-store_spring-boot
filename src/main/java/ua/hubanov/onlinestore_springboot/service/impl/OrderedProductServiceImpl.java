package ua.hubanov.onlinestore_springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.hubanov.onlinestore_springboot.entity.Order;
import ua.hubanov.onlinestore_springboot.entity.OrderedProduct;
import ua.hubanov.onlinestore_springboot.repository.OrderedProductRepository;
import ua.hubanov.onlinestore_springboot.service.OrderService;
import ua.hubanov.onlinestore_springboot.service.OrderedProductService;

import java.util.Set;

@Service
public class OrderedProductServiceImpl implements OrderedProductService {
//    private final OrderedProductRepository orderedProductRepository;
//    private final OrderService orderService;
//
//    @Autowired
//    public OrderedProductServiceImpl(OrderedProductRepository orderedProductRepository,
//                                     OrderService orderService) {
//        this.orderedProductRepository = orderedProductRepository;
//        this.orderService = orderService;
//    }

//    @Override
//    public void save(OrderedProduct orderedProduct) {
//        orderedProductRepository.save(orderedProduct);
//    }
//
//    @Override
//    public Set<OrderedProduct> findAllByOrder(Order order) {
//        return orderedProductRepository.findAllByOrder(order);
//    }
//
//    @Override
//    public Set<OrderedProduct> findAllByOrderId(Long orderId) throws Exception {
//        return findAllByOrder(orderService.findOrderById(orderId));
//    }
}
