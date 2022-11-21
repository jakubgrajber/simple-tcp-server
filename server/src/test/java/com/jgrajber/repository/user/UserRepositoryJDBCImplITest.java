package com.jgrajber.repository.user;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.jgrajber.db.PGDataSourceFactory.createPSQLDataSource;
import static org.junit.jupiter.api.Assertions.*;

/***
 * Start docker container with PostrgreSQL and init script
 * before running following tests.
 */

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

    @Test
    void givenLogin_whenGetIfByLogin_thenReturnLogin() {

        // given
        String login = "user1";
        long id = 1L;

        // when
        long idFromDb = userRepository.getIdByLogin(login);

        // then
        assertEquals(id, idFromDb);
    }
}