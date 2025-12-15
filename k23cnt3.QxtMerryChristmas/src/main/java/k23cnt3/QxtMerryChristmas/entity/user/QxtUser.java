package k23cnt3.QxtMerryChristmas.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "qxt_users")
@Getter
@Setter
public class QxtUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qxt_id")
    private Long id;

    @Column(name = "qxt_full_name", nullable = false, length = 150)
    private String fullName;

    @Column(name = "qxt_email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "qxt_password", nullable = false, length = 255)
    private String password;

    @Column(name = "qxt_phone", length = 20)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "qxt_role", nullable = false, length = 20)
    private QxtUserRole role;  // CLIENT / ADMIN / SHIPPER
}
