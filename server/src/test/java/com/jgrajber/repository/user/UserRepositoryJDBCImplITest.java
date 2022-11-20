package com.jgrajber.repository.user;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.jgrajber.db.PGDataSourceFactory.createPSQLDataSource;
import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryJDBCImplITest {

    private UserRepositoryJDBCImpl userRepository = new UserRepositoryJDBCImpl(createPSQLDataSource());

    private static final String PASSWORD = "test";

    @Test
    void givenValidLogin_whenGetPasswordByUserLogin_thenReturnPassword() {

        // given
        String login = "user1";

        // when
        Optional<String> passwordByUserLogin = userRepository.getPasswordByUserLogin(login);

        // then
        assertTrue(passwordByUserLogin.isPresent());
        assertEquals(PASSWORD, passwordByUserLogin.get());
    }

    @Test
    void givenInvalidLogin_whenGetPasswordByUserLogin_thenReturnEmptyOptional() {

        // given
        String login = "missing-login";

        // when
        Optional<String> passwordByUserLogin = userRepository.getPasswordByUserLogin(login);

        // then
        assertTrue(passwordByUserLogin.isEmpty());
    }
}