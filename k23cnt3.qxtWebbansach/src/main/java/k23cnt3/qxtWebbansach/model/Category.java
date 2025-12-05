package k23cnt3.qxtWebbansach.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Book> books;
}
