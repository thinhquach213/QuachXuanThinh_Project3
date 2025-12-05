package k23cnt3.qxtWebbansach.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Quan hệ 1-n với CartItem
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;

    // Quan hệ 1-n với Order
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    // Quan hệ 1-n với Review
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews;
}

enum Role {
    USER,
    ADMIN
}
