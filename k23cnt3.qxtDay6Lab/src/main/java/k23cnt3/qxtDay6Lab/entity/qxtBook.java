package k23cnt3.qxtDay6Lab.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class qxtBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;
    private String name;
    private String description;
    private String imgUrl;
    private Integer quantity;
    private Double price;
    private Boolean isActive;

    // Tạo mối quan hệ với bảng author
    @ManyToMany
    @JoinTable(
            name = "qxt_book_author",
            joinColumns = @JoinColumn(name = "qxtBookId"),
            inverseJoinColumns = @JoinColumn(name = "qxtAuthorId")
    )
    private List<qxtAuthor> qxtAuthors = new ArrayList<>();
}
