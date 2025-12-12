package k23cnt3.qxtWebbansach.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "qxt_reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qxt_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "qxt_user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "qxt_product_id")
    private Product product;

    @Column(name = "qxt_content")
    private String content;

    @Column(name = "qxt_rating")
    private Integer rating; // 1-5

    @Column(name = "qxt_created_at")
    private LocalDateTime createdAt;
}
