package com.books.readinglist.service.application;

import com.books.readinglist.domain.enumerations.BookStatus;
import com.books.readinglist.domain.enumerations.Genre;
import com.books.readinglist.dto.CreateBookDto;
import com.books.readinglist.dto.DisplayBookDto;

import java.util.List;

public interface BookApplicationService {
    List<DisplayBookDto> findAll(String username);

    DisplayBookDto findById(Long id);

    DisplayBookDto update(Long id, CreateBookDto createBookDto, String username);

    DisplayBookDto save(CreateBookDto createBookDto, String username);

    void deleteById(Long id);

    List<DisplayBookDto> findAllByStatusAndUser(BookStatus status, String username);
    List<DisplayBookDto> findAllByUser(String username);
    List<DisplayBookDto> findAllByUserAndGenreIgnoreCase(Genre genre, String username);
    DisplayBookDto startReading(Long id);
    DisplayBookDto finishReading(Long id);
}
