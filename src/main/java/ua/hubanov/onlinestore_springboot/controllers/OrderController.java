package ua.hubanov.onlinestore_springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ua.hubanov.onlinestore_springboot.entity.OrderedProduct;
import ua.hubanov.onlinestore_springboot.entity.User;
import ua.hubanov.onlinestore_springboot.service.OrderService;
import ua.hubanov.onlinestore_springboot.service.OrderedProductService;

import java.util.Optional;
import java.util.Set;

@Controller
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/user/cart/order")
    public String makeOrder(@AuthenticationPrincipal User user) {

        orderService.makeOrder(user);

        return "redirect:/user/cart";
    }

    @GetMapping("/admin/orders")
    public String allOrders(Model model) {
        model.addAttribute("notApprovedOrders", orderService.findAllByApprovedFalse());
        model.addAttribute("approvedOrders", orderService.findAllByApprovedTrue());
        return "/admin/orders";
    }

    @GetMapping("/admin/orders/approve")
    public String approveOrder(@RequestParam("orderId") Long orderId) throws Exception{
        orderService.approveOrder(orderId);
        return "redirect:/admin/orders";
    }

    @GetMapping("/admin/orders/{orderId}")
    public String orderInfo(@PathVariable("orderId") Long orderId, Model model) throws Exception {
        Set<OrderedProduct> orderedProducts = orderService.findAllOrderedProductByOrderId(orderId);
        model.addAttribute("orderedProducts", orderedProducts);
        model.addAttribute("totalPrice", orderService.getTotal(orderedProducts));
        return "/admin/order_info";
    }

    @GetMapping("/admin/orders/decline")
    public String declineOrder(@RequestParam("orderId") Long orderId) throws Exception{
 //       orderService.declineOrder(orderId);
        return "redirect:/admin/orders";
    }
}
