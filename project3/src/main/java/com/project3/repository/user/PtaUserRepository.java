package com.project3.repository.user;

import com.project3.entity.user.PtaUser;
import com.project3.entity.user.PtaUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PtaUserRepository extends JpaRepository<PtaUser, Long> {

    // Lọc user theo role
    List<PtaUser> findByRole(PtaUserRole role);

    // Dùng cho login
    Optional<PtaUser> findByEmail(String email);

    Optional<PtaUser> findByEmailAndPassword(String email, String password);
}