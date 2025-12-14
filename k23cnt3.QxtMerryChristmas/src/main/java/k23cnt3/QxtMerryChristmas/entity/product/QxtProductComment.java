package k23cnt3.QxtMerryChristmas.entity.product;

import k23cnt3.QxtMerryChristmas.entity.user.QxtUser;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "qxt_product_comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QxtProductComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private int star; // 1–5

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private QxtProduct product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private QxtUser user;
}
