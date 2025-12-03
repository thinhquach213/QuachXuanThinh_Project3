package com.qxt.qxtlab09.entity;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "qxt_author")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String imgUrl;
    private String email;
    private String phone;
    private String address;
    private Boolean isActive;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookAuthor> bookAuthors = new ArrayList<>();
}
