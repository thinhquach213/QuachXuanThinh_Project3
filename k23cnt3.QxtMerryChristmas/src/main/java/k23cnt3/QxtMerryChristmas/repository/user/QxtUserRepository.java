package k23cnt3.QxtMerryChristmas.repository.user;

import k23cnt3.QxtMerryChristmas.entity.user.QxtUser;
import k23cnt3.QxtMerryChristmas.entity.user.QxtUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QxtUserRepository extends JpaRepository<QxtUser, Long> {

    // Lọc user theo role
    List<QxtUser> findByRole(QxtUserRole role);

    // Dùng cho login
    Optional<QxtUser> findByEmail(String email);

    Optional<QxtUser> findByEmailAndPassword(String email, String password);
}