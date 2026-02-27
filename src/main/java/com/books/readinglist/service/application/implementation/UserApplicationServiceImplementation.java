package com.books.readinglist.service.application.implementation;

import com.books.readinglist.domain.exceptions.UserNotFoundException;
import com.books.readinglist.domain.models.User;
import com.books.readinglist.dto.CreateUserDto;
import com.books.readinglist.dto.DisplayUserDto;
import com.books.readinglist.dto.LoginResponseDto;
import com.books.readinglist.dto.LoginUserDto;
import com.books.readinglist.helpers.JwtHelper;
import com.books.readinglist.service.application.UserApplicationService;
import com.books.readinglist.service.domain.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationServiceImplementation implements UserApplicationService {
    private final UserService userService;
    private final JwtHelper jwtHelper;

    public UserApplicationServiceImplementation(UserService userService, JwtHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public DisplayUserDto register(CreateUserDto createUserDto) {
        User user = userService.register(createUserDto.username(), createUserDto.password(), createUserDto.repeatPassword(),
                createUserDto.name(), createUserDto.surname(), createUserDto.role());
        return DisplayUserDto.fromUser(user);
    }

    @Override
    public LoginResponseDto login(LoginUserDto loginUserDto) {
        User user = userService.login(loginUserDto.username(), loginUserDto.password());
        String token=jwtHelper.generateToken(user);
        return new LoginResponseDto(token);
    }

    @Override
    public DisplayUserDto findByUsername(String username) {
        User user = userService.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        return DisplayUserDto.fromUser(user);
    }
}