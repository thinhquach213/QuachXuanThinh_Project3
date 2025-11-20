package k23cnt3.qxtDay6Lab.repository;

import k23cnt3.qxtDay6Lab.entity.qxtBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface qxtBookRepository extends JpaRepository<qxtBook, Long> {
}
