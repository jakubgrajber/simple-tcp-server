package com.jgrajber.repository.user;

import java.util.Optional;

public interface UserRepository {

    Optional<String> getPasswordByUserLogin(String login);

    long getIdByLogin(String login);
}
