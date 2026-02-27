package com.books.readinglist.service.application.implementation;

import com.books.readinglist.domain.enumerations.BookStatus;
import com.books.readinglist.domain.enumerations.Genre;
import com.books.readinglist.domain.exceptions.BookNotFoundException;
import com.books.readinglist.domain.exceptions.UserNotFoundException;
import com.books.readinglist.domain.models.Book;
import com.books.readinglist.domain.models.User;
import com.books.readinglist.dto.CreateBookDto;
import com.books.readinglist.dto.DisplayBookDto;
import com.books.readinglist.service.application.BookApplicationService;
import com.books.readinglist.service.domain.BookService;
import com.books.readinglist.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookApplicationServiceImplementation implements BookApplicationService {

    private final BookService bookService;
    private final UserService userService;

    public BookApplicationServiceImplementation(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @Override
    public List<DisplayBookDto> findAll(String username) {
        User user = userService.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        return DisplayBookDto.fromBooks(bookService.findAllByUser(user));
    }

    @Override
    public DisplayBookDto findById(Long id) {
        Book book = bookService.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        return DisplayBookDto.fromBook(book);
    }

    @Override
    public DisplayBookDto update(Long id, CreateBookDto createBookDto, String username) {
        User user = userService.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        return DisplayBookDto.fromBook(bookService.update(id, createBookDto.toBook(user)));
    }

    @Override
    public DisplayBookDto save(CreateBookDto createBookDto, String username) {
        User user = userService.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        return DisplayBookDto.fromBook(bookService.save(createBookDto.toBook(user)));

    }

    @Override
    public void deleteById(Long id) {
        bookService.deleteById(id);
    }

    @Override
    public List<DisplayBookDto> findAllByStatusAndUser(BookStatus status, String username) {
        User user = userService.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        return DisplayBookDto.fromBooks(bookService.findAllByStatusAndUser(status, user));
    }

    @Override
    public List<DisplayBookDto> findAllByUser(String username) {
        User user = userService.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        return DisplayBookDto.fromBooks(bookService.findAllByUser(user));
    }

    @Override
    public List<DisplayBookDto> findAllByUserAndGenreIgnoreCase(Genre genre, String username) {
        User user = userService.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        return DisplayBookDto.fromBooks(bookService.findAllByUserAndGenreIgnoreCase(genre, user));
    }

    @Override
    public DisplayBookDto startReading(Long id) {
        return DisplayBookDto.fromBook(bookService.startReading(id));
    }

    @Override
    public DisplayBookDto finishReading(Long id) {
        return DisplayBookDto.fromBook(bookService.finishReading(id));
    }
}
