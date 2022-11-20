package com.jgrajber.repository.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class UserRepositoryJDBCImpl implements UserRepository {

    private final DataSource dataSource;

    @Autowired
    public UserRepositoryJDBCImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<String> getPasswordByUserLogin(String login) {

        String query = """
                SELECT u.password
                FROM app_user AS u
                WHERE u.login = ?
                """;

        String password = null;

        try (var connection = dataSource.getConnection();
             var ps = connection.prepareStatement(query)) {

            ps.setString(1, login);

            try (var rs = ps.executeQuery()) {
                while (rs.next()) {
                    password = rs.getString("password");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(password);
    }
}
