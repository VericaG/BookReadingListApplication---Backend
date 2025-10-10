package com.books.readinglist.service.application;

import com.books.readinglist.dto.CreateUserDto;
import com.books.readinglist.dto.DisplayUserDto;
import com.books.readinglist.dto.LoginResponseDto;
import com.books.readinglist.dto.LoginUserDto;

public interface UserApplicationService {
    DisplayUserDto register(CreateUserDto createUserDto);

    LoginResponseDto login(LoginUserDto loginUserDto);

    DisplayUserDto findByUsername(String username);
}