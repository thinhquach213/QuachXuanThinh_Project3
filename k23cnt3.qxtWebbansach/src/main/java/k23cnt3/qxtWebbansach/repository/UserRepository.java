package k23cnt3.qxtWebbansach.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import k23cnt3.qxtWebbansach.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);  // CODE GỐC

    // ⭐ BỔ SUNG — không làm thay đổi logic cũ
    Optional<User> findByEmail(String email);
}
