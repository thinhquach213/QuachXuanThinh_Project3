package k23cnt3.QxtMerryChristmas.entity.order;

import k23cnt3.QxtMerryChristmas.entity.user.QxtUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "qxt_orders")
@Getter
@Setter
public class QxtOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qxt_id")
    private Long id;

    @Column(name = "qxt_customer_name", nullable = false, length = 150)
    private String customerName;

    @Column(name = "qxt_phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "qxt_address", nullable = false, length = 255)
    private String address;

    @Column(name = "qxt_note", length = 500)
    private String note;

    @Column(name = "qxt_total_amount", nullable = false)
    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "qxt_status", nullable = false, length = 30)
    private QxtOrderStatus status;

    @Column(name = "qxt_created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "qxt_user_id")
    private QxtUser user;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<QxtOrderItem> items;
}
