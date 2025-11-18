package k23cnt.tvcDay06Lab.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class tvcAuthor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long tvcId;
    String tvcCode;
    String tvcName;
    String tvcDescription;
    String tvcImgUrl;
    String tvcEmail;
    String tvcPhone;
    String tvcAddress;
    Boolean tvcActive;

    // Tạo mỗi quan hệ với bảng tvcBook
    @ManyToMany(mappedBy = "tvcAuthor")
    List<tvcBook> tvcBooks = new ArrayList<>();
}
