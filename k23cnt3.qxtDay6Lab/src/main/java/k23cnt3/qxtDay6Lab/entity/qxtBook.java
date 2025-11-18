package k23cnt3.qxtDay6Lab.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class qxtBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long qxtId;
    private String qxtCode;
    private String qxtName;
    private String qxtDescription;
    private String qxtImgUrl;
    private Integer qxtQuantity;
    private Double qxtPrice;
    private Boolean qxtActive;
    // Tạo mối quan hệ với bảng author
    @ManyToMany
    @JoinTable(
            name = "qxt_Book_author",
            joinColumns = @JoinColumn(name = "qxtBookId"),
            inverseJoinColumns = @JoinColumn(name =
                    "qxtAuthorId")
    )
    private List<qxtAuthor> authors = new ArrayList<>();
}
