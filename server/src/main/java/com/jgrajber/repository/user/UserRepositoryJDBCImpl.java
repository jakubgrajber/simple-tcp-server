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
                if (rs.next()) {
                    password = rs.getString("password");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(password);
    }

    /***
     * Called only when user is already logged in.
     *
     * @param login of the user
     * @return logged-in user id
     */
    @Override
    public long getIdByLogin(String login) {

        String query = """
                SELECT u.id
                FROM app_user AS u
                WHERE u.login = ?
                """;

        long id = -1;

        try (var connection = dataSource.getConnection();
             var ps = connection.prepareStatement(query)) {

            ps.setString(1, login);

            try (var rs = ps.executeQuery()) {
                if (rs.next()) {
                    id = rs.getLong("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }
}
