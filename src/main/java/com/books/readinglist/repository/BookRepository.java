package com.books.readinglist.repository;

import com.books.readinglist.domain.enumerations.BookStatus;
import com.books.readinglist.domain.enumerations.Genre;
import com.books.readinglist.domain.models.Book;
import com.books.readinglist.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByStatusAndUser(BookStatus status, User user);
    List<Book> findAllByUser(User user);
    List<Book> findAllByGenreAndUser(Genre genre, User user);
}
