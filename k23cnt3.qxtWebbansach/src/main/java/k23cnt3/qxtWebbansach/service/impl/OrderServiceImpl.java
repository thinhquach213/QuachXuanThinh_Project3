package k23cnt3.qxtWebbansach.service.impl;

import k23cnt3.qxtWebbansach.dto.OrderDTO;
import k23cnt3.qxtWebbansach.model.CartItem;
import k23cnt3.qxtWebbansach.model.Order;
import k23cnt3.qxtWebbansach.model.OrderItem;
import k23cnt3.qxtWebbansach.model.Book;
import k23cnt3.qxtWebbansach.repository.CartItemRepository;
import k23cnt3.qxtWebbansach.repository.OrderItemRepository;
import k23cnt3.qxtWebbansach.repository.OrderRepository;
import k23cnt3.qxtWebbansach.repository.BookRepository;
import k23cnt3.qxtWebbansach.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartItemRepository cartItemRepository;
    private final BookRepository bookRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderItemRepository orderItemRepository,
                            CartItemRepository cartItemRepository,
                            BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.cartItemRepository = cartItemRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(OrderDTO::fromOrder).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId).stream().map(OrderDTO::fromOrder).collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        return orderRepository.findById(id).map(OrderDTO::fromOrder).orElse(null);
    }

    @Override
    public Order createOrder(Long userId) {
        List<CartItem> cartItems = cartItemRepository.findByUserId(userId);
        if(cartItems.isEmpty()) throw new RuntimeException("Cart is empty");

        BigDecimal total = cartItems.stream()
                .map(item -> item.getBook().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = new Order();
        order.setUserId(userId);
        order.setTotalAmount(total);
        order.setStatus("PENDING");
        order = orderRepository.save(order);

        for(CartItem ci : cartItems){
            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setBook(ci.getBook());
            oi.setQuantity(ci.getQuantity());
            oi.setPrice(ci.getBook().getPrice());
            orderItemRepository.save(oi);

            // giảm số lượng sách tồn kho
            Book book = ci.getBook();
            book.setQuantity(book.getQuantity() - ci.getQuantity());
            bookRepository.save(book);
        }

        cartItemRepository.deleteByUserId(userId);

        return order;
    }

    @Override
    public Order updateOrderStatus(Long orderId, String status) {
        return orderRepository.findById(orderId).map(order -> {
            order.setStatus(status);
            return orderRepository.save(order);
        }).orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
