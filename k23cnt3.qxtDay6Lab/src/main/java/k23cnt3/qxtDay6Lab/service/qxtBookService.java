package k23cnt3.qxtDay6Lab.service;

import k23cnt3.qxtDay6Lab.entity.qxtBook;
import k23cnt3.qxtDay6Lab.repository.qxtBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class qxtBookService {

    @Autowired
    private qxtBookRepository bookRepository;

    public List<qxtBook> getAllBooks() {
        return bookRepository.findAll();
    }

    public qxtBook saveBook(qxtBook book) {
        return bookRepository.save(book);
    }

    public qxtBook getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
