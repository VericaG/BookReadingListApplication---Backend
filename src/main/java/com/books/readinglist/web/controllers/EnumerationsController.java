package com.books.readinglist.web.controllers;

import com.books.readinglist.domain.enumerations.BookStatus;
import com.books.readinglist.domain.enumerations.Genre;
import com.books.readinglist.domain.enumerations.Role;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/enumerations")
public class EnumerationsController {

    @GetMapping("/bookStatus")
    public List<String> getAllStatuses(){
        return Arrays.stream(BookStatus.values()).map(Enum::name).collect(Collectors.toList());
    }


    @GetMapping("/genre")
    public List<String> getAllCategories(){
        return Arrays.stream(Genre.values()).map(Enum::name).collect(Collectors.toList());
    }

    @GetMapping("/role")
    public List<String> getAllRoles(){
        return Arrays.stream(Role.values()).map(Enum::name).collect(Collectors.toList());
    }
}