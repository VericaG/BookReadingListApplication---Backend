package com.books.readinglist.dto;

import com.books.readinglist.domain.enumerations.Role;
import com.books.readinglist.domain.models.User;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayUserDto(
        String username,
        String name,
        String surname,
        Role role) {

    public static DisplayUserDto from(User user) {
        return new DisplayUserDto(
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getRole()
        );
    }

    public static List<DisplayUserDto> from(List<User> users){
        return users.stream().map(DisplayUserDto::from).collect(Collectors.toList());
    }

}