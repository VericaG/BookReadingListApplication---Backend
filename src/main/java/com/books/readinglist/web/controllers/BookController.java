package com.books.readinglist.web.controllers;

import com.books.readinglist.domain.enumerations.BookStatus;
import com.books.readinglist.domain.enumerations.Genre;
import com.books.readinglist.domain.models.User;
import com.books.readinglist.dto.CreateBookDto;
import com.books.readinglist.dto.DisplayBookDto;
import com.books.readinglist.service.application.BookApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookApplicationService bookApplicationService;

    public BookController(BookApplicationService bookApplicationService) {
        this.bookApplicationService = bookApplicationService;
    }

    @GetMapping
    public List<DisplayBookDto> findAll() {
        return bookApplicationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayBookDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(bookApplicationService.findById(id));
    }


    @GetMapping("/statuses")
    public List<String> getBookStatus() {
        return Arrays.stream(BookStatus.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/genres")
    public List<String> getGenres() {
        return Arrays.stream(Genre.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }


    @PostMapping("/add")
    public ResponseEntity<DisplayBookDto> save(@RequestBody CreateBookDto book, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(bookApplicationService.save(book, user.getUsername()));
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayBookDto> update(@PathVariable Long id, @RequestBody CreateBookDto book, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(bookApplicationService.update(id, book, user.getUsername()));
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookApplicationService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/startReading/{id}")
    public ResponseEntity<DisplayBookDto> startReading(@PathVariable Long id){
        return ResponseEntity.ok(bookApplicationService.startReading(id));
    }

    @PutMapping("/finishReading/{id}")
    public ResponseEntity<DisplayBookDto> finishReading(@PathVariable Long id){
        return ResponseEntity.ok(bookApplicationService.finishReading(id));
    }
}
