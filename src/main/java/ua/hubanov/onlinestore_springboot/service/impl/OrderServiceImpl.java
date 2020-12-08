package ua.hubanov.onlinestore_springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.hubanov.onlinestore_springboot.entity.Order;
import ua.hubanov.onlinestore_springboot.entity.OrderedProduct;
import ua.hubanov.onlinestore_springboot.entity.Product;
import ua.hubanov.onlinestore_springboot.entity.User;
import ua.hubanov.onlinestore_springboot.repository.OrderRepository;
import ua.hubanov.onlinestore_springboot.service.CartService;
import ua.hubanov.onlinestore_springboot.service.OrderService;
import ua.hubanov.onlinestore_springboot.service.OrderedProductService;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderedProductService orderedProductService;
    private final CartService cartService;

    @Autowired
    public OrderServiceImpl(OrderedProductService orderedProductService, OrderRepository orderRepository,
                            CartService cartService) {
        this.orderRepository = orderRepository;
        this.orderedProductService = orderedProductService;
        this.cartService = cartService;
    }

    // TODO method is unfinished (add removing products from cart)
    @Override
    public void makeOrder(User user) {
        Map<Product, Integer> productsForOrder = cartService.getAllProductsInCart(user);

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(new Date());
        orderRepository.save(order);

        Set<OrderedProduct> orderedProducts = new HashSet<>();
        for (Map.Entry<Product, Integer> entry : productsForOrder.entrySet()) {
            OrderedProduct orderedProduct = new OrderedProduct();
//            orderedProduct.setProduct(entry.getKey());
            orderedProduct.setName(entry.getKey().getName());
            orderedProduct.setQuantity(entry.getValue());
            orderedProduct.setPrice(entry.getKey().getPrice());
            orderedProduct.setCategory(entry.getKey().getCategory());
            orderedProduct.setDescription(entry.getKey().getDescription());
            orderedProduct.setOrder(order);
            orderedProducts.add(orderedProduct);
            orderedProductService.save(orderedProduct);
        }

        order.setOrderedProducts(orderedProducts);
        orderRepository.save(order);

        cartService.clearProductsFromCart(user);
    }

    @Override
    public Set<OrderedProduct> getAllOrderedProducts(User user) {
        Set<OrderedProduct> allOrderedProducts = new HashSet<>();

        Set<Order> allOrders = orderRepository.findAllByUser(user);
//        Set<Order> allOrders = user.getCart().getOrders();

        for (Order x : allOrders) {
            allOrderedProducts.addAll(x.getOrderedProducts());
        }

        return allOrderedProducts;
    }
}
