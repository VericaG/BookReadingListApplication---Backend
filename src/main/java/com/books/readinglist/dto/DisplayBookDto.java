package com.books.readinglist.dto;

import com.books.readinglist.domain.enumerations.BookStatus;
import com.books.readinglist.domain.enumerations.Genre;
import com.books.readinglist.domain.models.Book;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record DisplayBookDto(
        Long id,
        String title,
        String author,
        Genre genre,
        BookStatus status,
        LocalDate dateAdded
) {

    public static DisplayBookDto from(Book book) {
        return new DisplayBookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getGenre(),
                book.getStatus(),
                book.getDateAdded()
        );
    }

    public static List<DisplayBookDto> from (List<Book> books) {
        return books.stream().map(DisplayBookDto::from).collect(Collectors.toList());
    }
}
