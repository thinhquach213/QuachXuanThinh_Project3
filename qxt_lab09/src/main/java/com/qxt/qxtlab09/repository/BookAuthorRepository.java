package com.qxt.qxtlab09.repository;

import com.qxt.qxtlab09.entity.BookAuthor;
import com.qxt.qxtlab09.entity.BookAuthorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookAuthorRepository extends JpaRepository<BookAuthor, BookAuthorId> {
}
