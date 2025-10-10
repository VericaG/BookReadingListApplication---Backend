package com.books.readinglist.service.application;

import com.books.readinglist.domain.enumerations.BookStatus;
import com.books.readinglist.domain.enumerations.Genre;
import com.books.readinglist.domain.models.Book;
import com.books.readinglist.domain.models.User;
import com.books.readinglist.dto.CreateBookDto;
import com.books.readinglist.dto.DisplayBookDto;
import com.books.readinglist.dto.DisplayUserDto;

import java.util.List;
import java.util.Optional;

public interface BookApplicationService {
    List<DisplayBookDto> findAll();

    DisplayBookDto findById(Long id);

    DisplayBookDto update(Long id, CreateBookDto createBookDto, String username);

    DisplayBookDto save(CreateBookDto createBookDto, String username);

    void deleteById(Long id);

    List<DisplayBookDto> findAllByStatusAndUser(BookStatus status, User user);
    List<DisplayBookDto> findAllByUser(User user);
    List<DisplayBookDto> findAllByGenreIgnoreCase(Genre genre);
    DisplayBookDto startReading(Long id);
    DisplayBookDto finishReading(Long id);
}
