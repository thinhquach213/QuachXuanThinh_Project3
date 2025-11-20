package k23cnt3.qxtDay6Lab.repository;

import k23cnt3.qxtDay6Lab.entity.qxtAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface qxtAuthorRepository extends JpaRepository<qxtAuthor, Long> {
}
