package ua.hubanov.onlinestore_springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.hubanov.onlinestore_springboot.entity.Order;
import ua.hubanov.onlinestore_springboot.entity.OrderedProduct;
import ua.hubanov.onlinestore_springboot.entity.Product;
import ua.hubanov.onlinestore_springboot.entity.User;
import ua.hubanov.onlinestore_springboot.repository.OrderRepository;
import ua.hubanov.onlinestore_springboot.repository.OrderedProductRepository;
import ua.hubanov.onlinestore_springboot.service.CartService;
import ua.hubanov.onlinestore_springboot.service.OrderService;
import ua.hubanov.onlinestore_springboot.service.OrderedProductService;

import java.math.BigDecimal;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderedProductRepository orderedProductRepository;
    private final OrderRepository orderRepository;
    private final CartService cartService;

    @Autowired
    public OrderServiceImpl(OrderedProductRepository orderedProductRepository,
                            OrderRepository orderRepository,
                            CartService cartService) {
        this.orderedProductRepository = orderedProductRepository;
        this.orderRepository = orderRepository;
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
            orderedProduct.setProductId(entry.getKey().getId());
            orderedProduct.setName(entry.getKey().getName());
            orderedProduct.setQuantity(entry.getValue());
            orderedProduct.setPrice(entry.getKey().getPrice());
            orderedProduct.setCategory(entry.getKey().getCategory());
            orderedProduct.setDescription(entry.getKey().getDescription());
            orderedProduct.setOrder(order);
            orderedProducts.add(orderedProduct);
            saveProduct(orderedProduct);
        }

        order.setOrderedProducts(orderedProducts);
        orderRepository.save(order);

        cartService.clearProductsFromCart(user);
    }

    @Override
    public Set<OrderedProduct> getAllOrderedProductsOfUser(User user) {
        Set<OrderedProduct> allOrderedProducts = new HashSet<>();

        Set<Order> allOrders = orderRepository.findAllByUser(user);
//        Set<Order> allOrders = user.getCart().getOrders();

        for (Order x : allOrders) {
            allOrderedProducts.addAll(x.getOrderedProducts());
        }

        return allOrderedProducts;
    }

    @Override
    public Set<OrderedProduct> getAllApprovedOrderedProductsOfUser(User user) {
        Set<OrderedProduct> allApprovedOrderedProducts = new HashSet<>();
        Set<Order> allApprovedOrdersOfUser = orderRepository.findAllByUserAndIsApprovedIsTrue(user);

        for (Order x : allApprovedOrdersOfUser) {
            allApprovedOrderedProducts.addAll(x.getOrderedProducts());
        }
        return allApprovedOrderedProducts;
    }

    @Override
    public Set<OrderedProduct> getAllNotApprovedOrderedProductsOfUser(User user) {
        Set<OrderedProduct> allNotApprovedOrderedProducts = new HashSet<>();
        Set<Order> allNotApprovedOrdersOfUser = orderRepository.findAllByUserAndIsApprovedIsFalse(user);

        for (Order x : allNotApprovedOrdersOfUser) {
            allNotApprovedOrderedProducts.addAll(x.getOrderedProducts());
        }
        return allNotApprovedOrderedProducts;
    }

    @Override
    public Set<Order> findAll() {
        return new HashSet<>(orderRepository.findAll());
    }

    @Override
    public Set<Order> findAllByApprovedFalse() {
        return orderRepository.findAllByIsApprovedIsFalse();
    }

    @Override
    public Set<Order> findAllByApprovedTrue() {
        return orderRepository.findAllByIsApprovedIsTrue();
    }

    @Override
    public void approveOrder(Long orderId) throws Exception {
        Order order = orderRepository.findById(orderId).orElseThrow(Exception::new);
        order.setApproved(true);
        orderRepository.save(order);
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {
        return orderRepository.findOneById(orderId).orElseThrow(Exception::new);
    }



    @Override
    public void saveProduct(OrderedProduct orderedProduct) {
        orderedProductRepository.save(orderedProduct);
    }

    @Override
    public Set<OrderedProduct> findAllOrderedProductByOrder(Order order) {
        return orderedProductRepository.findAllByOrder(order);
    }

    @Override
    public Set<OrderedProduct> findAllOrderedProductByOrderId(Long orderId) throws Exception {
        return findAllOrderedProductByOrder(findOrderById(orderId));
    }

    @Override
    public BigDecimal getTotal(Set<OrderedProduct> products) {
        return products.stream()
                .map(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

}
