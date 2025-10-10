package com.books.readinglist.dto;

import com.books.readinglist.domain.enumerations.BookStatus;
import com.books.readinglist.domain.enumerations.Genre;
import com.books.readinglist.domain.models.Book;
import com.books.readinglist.domain.models.User;

import java.time.LocalDate;

public record CreateBookDto(
        String title,
        String author,
        Genre genre,
        BookStatus status,
        LocalDate dateAdded) {

    public Book toBook(User user) {
        return new Book(title, author, genre, status, dateAdded, user);
    }
}
