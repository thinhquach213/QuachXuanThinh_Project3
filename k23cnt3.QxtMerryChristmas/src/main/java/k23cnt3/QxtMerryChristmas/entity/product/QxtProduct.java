package k23cnt3.QxtMerryChristmas.entity.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "qxt_product")
@Getter
@Setter
@NoArgsConstructor
public class QxtProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qxt_id")
    private Long id;          // đã ALTER thành BIGINT

    @Column(name = "qxt_name", nullable = false, length = 255)
    private String name;

    // chỉ lưu tên file ảnh
    @Column(name = "qxt_image", length = 500)
    private String image;

    @Column(name = "qxt_price")
    private Double price;

    @Column(name = "qxt_description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "qxt_category_id")
    private QxtCategory category;

    @Column(name = "qxt_tag")
    private String tag;
}
