package project3.service.impl;

import k23cnt3.qxtWebbansach.model.Book;
import k23cnt3.qxtWebbansach.repository.BookRepository;
import k23cnt3.qxtWebbansach.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getBooks(String keyword, Long categoryId) {
        if (keyword != null && !keyword.isEmpty()) {
            return bookRepository.findByTitleContainingIgnoreCase(keyword);
        } else if (categoryId != null) {
            return bookRepository.findByCategoryId(categoryId);
        }
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
