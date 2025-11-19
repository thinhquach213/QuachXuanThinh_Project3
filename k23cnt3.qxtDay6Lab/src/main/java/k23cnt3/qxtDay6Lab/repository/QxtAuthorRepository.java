package k23cnt3.qxtDay6Lab.repository;

import k23cnt3.qxtDay6Lab.entity.qxtAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QxtAuthorRepository extends JpaRepository<qxtAuthor, Long> {

    boolean existsByQxtCode(String qxtCode);

    // THÊM DÒNG NÀY
    List<qxtAuthor> findByQxtActiveTrue();
}

