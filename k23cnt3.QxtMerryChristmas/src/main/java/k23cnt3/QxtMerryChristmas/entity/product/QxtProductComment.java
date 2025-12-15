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
    @Column(name = "qxt_id")
    private Long id;

    @Column(name = "qxt_content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "qxt_star", nullable = false)
    private int star; // 1–5

    @Column(name = "qxt_created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "qxt_product_id")
    private QxtProduct product;

    @ManyToOne
    @JoinColumn(name = "qxt_user_id")
    private QxtUser user;
}
