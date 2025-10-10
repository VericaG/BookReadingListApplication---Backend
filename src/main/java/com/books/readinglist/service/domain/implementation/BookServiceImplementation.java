package com.books.readinglist.service.domain.implementation;

import com.books.readinglist.domain.enumerations.BookStatus;
import com.books.readinglist.domain.enumerations.Genre;
import com.books.readinglist.domain.exceptions.BookAlreadyInProgressException;
import com.books.readinglist.domain.exceptions.BookCanNotBeFinishedException;
import com.books.readinglist.domain.exceptions.BookNotFoundException;
import com.books.readinglist.domain.models.Book;
import com.books.readinglist.domain.models.User;
import com.books.readinglist.repository.BookRepository;
import com.books.readinglist.repository.UserRepository;
import com.books.readinglist.service.domain.BookService;
import com.books.readinglist.service.domain.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImplementation implements BookService {

    private final BookRepository bookRepository;
    private final UserService userService;

    public BookServiceImplementation(BookRepository bookRepository, UserService userService) {
        this.bookRepository = bookRepository;
        this.userService = userService;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book update(Long id, Book book) {
        Book existingBook = findById(id).orElseThrow(() -> new BookNotFoundException(id));
        if (book.getStatus() != null) {
            existingBook.setStatus(book.getStatus());
        }
        if (book.getGenre() != null) {
            existingBook.setGenre(book.getGenre());
        }
        if (book.getAuthor() != null) {
            existingBook.setAuthor(book.getAuthor());
        }
        if (book.getDateAdded() != null) {
            existingBook.setDateAdded(book.getDateAdded());
        }
        if (book.getUser() != null) {
            userService.findByUsername(book.getUser().getUsername())
                    .ifPresent(existingBook::setUser);
        }
        return bookRepository.save(existingBook);
    }


    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findAllByStatusAndUser(BookStatus status, User user) {
        return bookRepository.findAllByStatusAndUser(status, user);
    }

    @Override
    public List<Book> findAllByUser(User user) {
        return bookRepository.findAllByUser(user);
    }

    @Override
    public List<Book> findAllByGenreIgnoreCase(Genre genre) {
        return bookRepository.findAllByGenre(genre);
    }

    @Override
    @Transactional
    public Book startReading(Long id) {
        Book book = findById(id).orElseThrow(() -> new BookNotFoundException(id));
        if (book.getStatus() != BookStatus.TO_READ) {
            throw new BookAlreadyInProgressException(id);
        }
        book.setStatus(BookStatus.READING);
        return bookRepository.save(book);
    }

    @Override
    public Book finishReading(Long id) {
        Book book = findById(id).orElseThrow(() -> new BookNotFoundException(id));
        if (book.getStatus() != BookStatus.READING) {
            throw new BookCanNotBeFinishedException(id);
        }
        book.setStatus(BookStatus.FINISHED);
        return bookRepository.save(book);
    }
}
