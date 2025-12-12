package k23cnt3.qxtWebbansach.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import k23cnt3.qxtWebbansach.entity.News;

public interface NewsRepository extends JpaRepository<News, Long> {
}
