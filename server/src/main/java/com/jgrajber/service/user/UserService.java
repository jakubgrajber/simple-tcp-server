package com.jgrajber.service.user;

public interface UserService {
    boolean login(String login, String password);
    long getIdByLogin(String login);
}
