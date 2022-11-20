package com.jgrajber.service.user;

import com.jgrajber.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void givenCorrectCredentials_whenLogin_thenReturnTrue() {

        // given
        String login = "user1";
        String password = "test";

        // when
        BDDMockito.given(userRepository.getPasswordByUserLogin(login)).willReturn(Optional.of(password));
        boolean loginTest = userService.login(login, password);

        // then
        assertTrue(loginTest);
    }

    @Test
    void givenWrongPassword_whenLogin_thenReturnFalse() {

        // given
        String login = "user1";
        String password = "test";
        String wrongPassword = "wrong";

        // when
        BDDMockito.given(userRepository.getPasswordByUserLogin(login)).willReturn(Optional.of(password));
        boolean loginTest = userService.login(login, wrongPassword);

        // then
        assertFalse(loginTest);
    }

    @Test
    void givenWrongLogin_whenLogin_thenReturnEmptyOptional() {

        // given
        String login = "user1";
        String password = "test";

        // when
        BDDMockito.given(userRepository.getPasswordByUserLogin(anyString())).willReturn(Optional.empty());
        boolean loginTest = userService.login(login, password);

        // then
        assertFalse(loginTest);
    }
}