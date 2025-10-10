package com.books.readinglist.service.domain;

import com.books.readinglist.domain.enumerations.Role;
import com.books.readinglist.domain.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, String surname, Role role);

    User login(String username, String password);

    Optional<User> findByUsername(String username);
}