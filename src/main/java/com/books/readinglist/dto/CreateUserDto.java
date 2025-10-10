package com.books.readinglist.dto;

import com.books.readinglist.domain.enumerations.Role;
import com.books.readinglist.domain.models.User;

public record CreateUserDto(
        String username,
        String password,
        String repeatPassword,
        String name,
        String surname,
        Role role) {

    public User toUser(){
        return new User(username, password, name, surname, role);
    }

}