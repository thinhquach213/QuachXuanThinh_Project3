package k23cnt3.qxtDay6Lab.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class qxtAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long qxtId;

    String qxtCode;
    String qxtName;
    String qxtDescription;
    String qxtImgUrl;
    String qxtEmail;
    String qxtPhone;
    String qxtAddress;
    boolean qxtActive;

    @ManyToMany(mappedBy = "authors")   // phải trùng với QxtBook.authors
    List<qxtBook> books = new ArrayList<>();
}
