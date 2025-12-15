package k23cnt3.QxtMerryChristmas.entity.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "qxt_order_items")
public class QxtOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qxt_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qxt_order_id", nullable = false)
    private QxtOrder order;

    @Column(name = "qxt_product_id", nullable = false)
    private Long productId;

    @Column(name = "qxt_product_name", nullable = false, length = 255)
    private String productName;

    @Column(name = "qxt_product_price", nullable = false)
    private Double productPrice;

    @Column(name = "qxt_quantity", nullable = false)
    private Integer quantity;
}
