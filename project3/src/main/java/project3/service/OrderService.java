package project3.service;

import k23cnt3.qxtWebbansach.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getOrdersByUser(Long userId);
    List<Order> getAllOrders();
    Optional<Order> getOrderById(Long id);
    Order updateOrder(Order order);
    void deleteOrder(Long id);
}
