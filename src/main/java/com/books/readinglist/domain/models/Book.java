package com.books.readinglist.domain.models;

import com.books.readinglist.domain.enumerations.BookStatus;
import com.books.readinglist.domain.enumerations.Genre;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

    private LocalDate dateAdded = LocalDate.now();

    @ManyToOne
    private User user;

    public Book(String title, String author, Genre genre, BookStatus status, LocalDate dateAdded, User user) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.status = status;
        this.dateAdded = dateAdded;
        this.user = user;
    }
}
