package com.books.readinglist.service.domain;

import com.books.readinglist.domain.enumerations.BookStatus;
import com.books.readinglist.domain.enumerations.Genre;
import com.books.readinglist.domain.models.Book;
import com.books.readinglist.domain.models.User;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    Book update(Long id, Book book);

    Book save(Book book);

    void deleteById(Long id);

    List<Book> findAllByStatusAndUser(BookStatus status, User user);
    List<Book> findAllByUser(User user);
    List<Book> findAllByGenreIgnoreCase(Genre genre);
    Book startReading(Long id);
    Book finishReading(Long id);
}
