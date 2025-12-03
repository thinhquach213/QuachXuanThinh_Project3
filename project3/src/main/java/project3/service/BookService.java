package project3.service;

import k23cnt3.qxtWebbansach.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooks(String keyword, Long categoryId);
    Book getBookById(Long id);
    List<Book> getAllBooks();
}
