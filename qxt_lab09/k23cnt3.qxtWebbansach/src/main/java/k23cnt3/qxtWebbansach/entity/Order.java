package k23cnt3.qxtWebbansach.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "qxt_orders")   // Đổi tên bảng
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qxt_id")   // Đổi tên cột id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "qxt_user_id")  // Đổi tên cột khóa ngoại
    private User user;

    @Column(name = "qxt_order_date")
    private LocalDateTime orderDate;

    @Column(name = "qxt_total_price")
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "qxt_status")
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    // --- Thông tin người nhận ---
    @Column(name = "qxt_full_name")
    private String fullName;

    @Column(name = "qxt_phone")
    private String phone;

    @Column(name = "qxt_address")
    private String address;

    @Column(name = "qxt_payment_method")
    private String paymentMethod;

    // Getter – Setter (nếu bạn muốn giữ nguyên)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public Double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public List<OrderItem> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
}
