package com.jgrajber.service.user;

import com.jgrajber.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean login(String login, String password) {

        Optional<String> passwordByUserLogin = userRepository.getPasswordByUserLogin(login);

        if (passwordByUserLogin.isEmpty())
            return false;
        else
            return passwordByUserLogin.get().equals(password);
    }
}
