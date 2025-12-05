package k23cnt3.qxtWebbansach.service;

import k23cnt3.qxtWebbansach.dto.OrderDTO;
import k23cnt3.qxtWebbansach.model.Order;
import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();
    List<OrderDTO> getOrdersByUser(Long userId);
    OrderDTO getOrderById(Long id);
    Order createOrder(Long userId);
    Order updateOrderStatus(Long orderId, String status);
}
