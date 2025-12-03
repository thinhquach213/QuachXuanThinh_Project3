package com.qxt.qxtlab09.service;

import com.qxt.qxtlab09.entity.Book;
import com.qxt.qxtlab09.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository repo;

    public List<Book> getAll() { return repo.findAll(); }
    public Book save(Book b) { return repo.save(b); }
    public Book findById(Long id) { return repo.findById(id).orElse(null); }
    public void delete(Long id) { repo.deleteById(id); }
}
