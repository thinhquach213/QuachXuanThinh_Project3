package k23cnt3.qxtWebbansach.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod = PaymentMethod.CASH;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    public enum Status { PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELLED }
    public enum PaymentMethod { CASH, CARD, ONLINE }
}
