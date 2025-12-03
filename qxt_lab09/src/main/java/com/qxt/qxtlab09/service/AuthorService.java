package com.qxt.qxtlab09.service;

import com.qxt.qxtlab09.entity.Author;
import com.qxt.qxtlab09.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository repo;

    public List<Author> getAll() { return repo.findAll(); }
    public Author save(Author a) { return repo.save(a); }
    public Author findById(Long id) { return repo.findById(id).orElse(null); }
}
