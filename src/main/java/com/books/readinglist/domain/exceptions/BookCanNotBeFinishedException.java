package com.books.readinglist.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookCanNotBeFinishedException extends RuntimeException {
    public BookCanNotBeFinishedException(Long id) {
        super(String.format("Book with id %d is not started or already finished!", id));
    }
}