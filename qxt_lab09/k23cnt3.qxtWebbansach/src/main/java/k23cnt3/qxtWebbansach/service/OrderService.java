package k23cnt3.qxtWebbansach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import k23cnt3.qxtWebbansach.entity.*;
import k23cnt3.qxtWebbansach.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Lấy tất cả order
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    // Lấy order theo user
    public List<Order> findByUser(User user) {
        return orderRepository.findByUser(user);
    }

    // Lấy order theo ID
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    // Lưu order
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    // Xóa order
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    // ⭐⭐⭐ HÀM TẠO ĐƠN HÀNG TỪ GIỎ HÀNG ⭐⭐⭐
    public Order createOrderFromCart(Cart cart, String fullName, String phone,
                                     String address, String paymentMethod) {

        Order order = new Order();
        order.setUser(cart.getUser());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.setFullName(fullName);
        order.setPhone(phone);
        order.setAddress(address);
        order.setPaymentMethod(paymentMethod);

        double total = 0;

        // Tạo danh sách sản phẩm từ giỏ hàng
        for (CartItem cartItem : cart.getItems()) {

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order); // Quan trọng
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice());

            total += cartItem.getQuantity() * cartItem.getProduct().getPrice();

            order.getOrderItems().add(orderItem); // Quan trọng
        }

        order.setTotalPrice(total);

        // ✔ cascade = ALL → OrderItem tự lưu theo Order
        return orderRepository.save(order);
    }
}
