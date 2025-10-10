package com.books.readinglist.service.application.implementation;

import com.books.readinglist.domain.enumerations.BookStatus;
import com.books.readinglist.domain.enumerations.Genre;
import com.books.readinglist.domain.exceptions.BookNotFoundException;
import com.books.readinglist.domain.exceptions.UserNotFoundException;
import com.books.readinglist.domain.models.Book;
import com.books.readinglist.domain.models.User;
import com.books.readinglist.dto.CreateBookDto;
import com.books.readinglist.dto.DisplayBookDto;
import com.books.readinglist.dto.DisplayUserDto;
import com.books.readinglist.service.application.BookApplicationService;
import com.books.readinglist.service.domain.BookService;
import com.books.readinglist.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookApplicationServiceImplementation implements BookApplicationService {

    private final BookService bookService;
    private final UserService userService;

    public BookApplicationServiceImplementation(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @Override
    public List<DisplayBookDto> findAll() {
        return DisplayBookDto.from(bookService.findAll());
    }

    @Override
    public DisplayBookDto findById(Long id) {
        Book book = bookService.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        return DisplayBookDto.from(book);
    }

    @Override
    public DisplayBookDto update(Long id, CreateBookDto createBookDto, String username) {
        User user = userService.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        return DisplayBookDto.from(bookService.update(id, createBookDto.toBook(user)));
    }

    @Override
    public DisplayBookDto save(CreateBookDto createBookDto, String username) {
        User user = userService.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        return DisplayBookDto.from(bookService.save(createBookDto.toBook(user)));

    }

    @Override
    public void deleteById(Long id) {
        bookService.deleteById(id);
    }

    @Override
    public List<DisplayBookDto> findAllByStatusAndUser(BookStatus status, User user) {
        return DisplayBookDto.from(bookService.findAllByStatusAndUser(status, user));
    }

    @Override
    public List<DisplayBookDto> findAllByUser(User user) {
        return DisplayBookDto.from(bookService.findAllByUser(user));
    }

    @Override
    public List<DisplayBookDto> findAllByGenreIgnoreCase(Genre genre) {
        return DisplayBookDto.from(bookService.findAllByGenreIgnoreCase(genre));
    }

    @Override
    public DisplayBookDto startReading(Long id) {
        return DisplayBookDto.from(bookService.startReading(id));
    }

    @Override
    public DisplayBookDto finishReading(Long id) {
        return DisplayBookDto.from(bookService.finishReading(id));
    }
}
