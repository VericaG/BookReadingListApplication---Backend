package com.books.readinglist.service.domain.implementation;

import com.books.readinglist.domain.enumerations.Role;
import com.books.readinglist.domain.exceptions.InvalidUserCredentialsException;
import com.books.readinglist.domain.exceptions.PasswordsDoNotMatchException;
import com.books.readinglist.domain.exceptions.UserNotFoundException;
import com.books.readinglist.domain.exceptions.UsernameAlreadyExistsException;
import com.books.readinglist.domain.models.User;
import com.books.readinglist.repository.UserRepository;
import com.books.readinglist.service.domain.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImplementation(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role role) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()
                || repeatPassword == null || repeatPassword.isEmpty()) {
            throw new InvalidUserCredentialsException();
        }

        if (this.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException(username);
        }

        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }

        return userRepository.save(new User(username, passwordEncoder.encode(password), name, surname, role));
    }

    @Override
    public User login(String username, String password) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty()){
            throw new InvalidUserCredentialsException();
        }
        User user = this.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidUserCredentialsException();
        }

        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }
}