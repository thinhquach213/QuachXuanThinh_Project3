package com.qxt.qxtlab09.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "qxt_book_author")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookAuthor {

    @EmbeddedId
    private BookAuthorId id = new BookAuthorId();

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "bookid")
    private Book book;

    @ManyToOne
    @MapsId("authorId")
    @JoinColumn(name = "authorid")
    private Author author;

    private Boolean isEditor = false;
}
