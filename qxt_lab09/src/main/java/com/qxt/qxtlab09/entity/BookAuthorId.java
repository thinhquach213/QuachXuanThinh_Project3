package com.qxt.qxtlab09.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookAuthorId implements Serializable {
    private Long bookId;
    private Long authorId;
}
