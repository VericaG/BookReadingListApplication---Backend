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

    public static DisplayUserDto fromUser(User user) {
        return new DisplayUserDto(
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getRole()
        );
    }

    public static List<DisplayUserDto> fromUserList(List<User> users){
        return users.stream().map(DisplayUserDto::fromUser).collect(Collectors.toList());
    }

}