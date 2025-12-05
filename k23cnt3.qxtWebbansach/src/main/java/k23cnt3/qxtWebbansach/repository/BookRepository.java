package k23cnt3.qxtWebbansach.repository;

import k23cnt3.qxtWebbansach.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByCategoryId(Long categoryId);
}
