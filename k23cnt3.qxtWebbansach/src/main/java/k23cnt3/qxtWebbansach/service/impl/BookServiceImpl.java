package k23cnt3.qxtWebbansach.service.impl;

import k23cnt3.qxtWebbansach.model.Book;
import k23cnt3.qxtWebbansach.repository.BookRepository;
import k23cnt3.qxtWebbansach.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        return bookRepository.findById(id).map(existing -> {
            existing.setTitle(book.getTitle());
            existing.setAuthor(book.getAuthor());
            existing.setDescription(book.getDescription());
            existing.setPrice(book.getPrice());
            existing.setQuantity(book.getQuantity());
            existing.setImageUrl(book.getImageUrl());
            existing.setCategory(book.getCategory());
            return bookRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> searchBooks(String keyword) {
        return bookRepository.findByTitleContainingIgnoreCase(keyword);
    }

    @Override
    public List<Book> getBooksByCategory(Long categoryId) {
        return bookRepository.findByCategoryId(categoryId);
    }
}
