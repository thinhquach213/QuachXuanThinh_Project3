package k23cnt3.qxtWebbansach.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    private int quantity = 1;
    private BigDecimal price;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
