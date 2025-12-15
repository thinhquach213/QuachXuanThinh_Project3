package k23cnt3.QxtMerryChristmas.entity.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "qxt_category")
@Getter
@Setter
public class QxtCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qxt_id")
    private Long id;   // dùng Long để đồng bộ với service & repo

    @Column(name = "qxt_name", nullable = false, length = 255)
    private String name;
}
