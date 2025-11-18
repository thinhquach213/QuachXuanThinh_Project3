package k23cnt.tvcDay06Lab.repository;

import k23cnt.tvcDay06Lab.entity.tvcAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TvcAuthorRepository extends JpaRepository<tvcAuthor, Long> {
}
