package com.books.readinglist.web.controllers;

import com.books.readinglist.dto.CreateUserDto;
import com.books.readinglist.dto.DisplayUserDto;
import com.books.readinglist.dto.LoginResponseDto;
import com.books.readinglist.dto.LoginUserDto;
import com.books.readinglist.service.application.UserApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @PostMapping("/register")
    public ResponseEntity<DisplayUserDto> register(@RequestBody CreateUserDto createUserDto){
        return ResponseEntity.ok(userApplicationService.register(createUserDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginUserDto loginUserDto){
        return ResponseEntity.ok(userApplicationService.login(loginUserDto));
    }
}