package k23cnt3.qxtlesson07.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String imgUrl;
    private Integer qty;
    private Double price;
    private Integer yearRelease;
    private String author;
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}