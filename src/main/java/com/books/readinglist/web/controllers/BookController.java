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
    public ResponseEntity<List<DisplayBookDto>> findAll(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(bookApplicationService.findAllByUser(user.getUsername()));
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<DisplayBookDto> bookDetails(@PathVariable Long id) {
        return ResponseEntity.ok(bookApplicationService.findById(id));
    }


    @PostMapping("/add")
    public ResponseEntity<DisplayBookDto> add(@RequestBody CreateBookDto book, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(bookApplicationService.save(book, user.getUsername()));
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayBookDto> edit(@PathVariable Long id, @RequestBody CreateBookDto book, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(bookApplicationService.update(id, book, user.getUsername()));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookApplicationService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/startReading/{id}")
    public ResponseEntity<DisplayBookDto> startReading(@PathVariable Long id) {
        return ResponseEntity.ok(bookApplicationService.startReading(id));
    }

    @PutMapping("/finishReading/{id}")
    public ResponseEntity<DisplayBookDto> finishReading(@PathVariable Long id) {
        return ResponseEntity.ok(bookApplicationService.finishReading(id));
    }
}
