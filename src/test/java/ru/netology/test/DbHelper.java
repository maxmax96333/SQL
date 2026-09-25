package ru.netology.test;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {

    private static final String URL = "jdbc:mysql://localhost:3306/app";
    private static final String USER = "app";
    private static final String PASSWORD = "pass";

    private DbHelper() {
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static String getVerificationCode(String login) {
        QueryRunner runner = new QueryRunner();

        String sql =
                "SELECT ac.code " +
                        "FROM auth_codes ac " +
                        "JOIN users u ON u.id = ac.user_id " +
                        "WHERE u.login = ? " +
                        "ORDER BY ac.created DESC " +
                        "LIMIT 1";

        try (Connection connection = getConnection()) {
            return runner.query(
                    connection,
                    sql,
                    new ScalarHandler<>(),
                    login
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getUserStatus(String login) {
        QueryRunner runner = new QueryRunner();

        String sql =
                "SELECT status " +
                        "FROM users " +
                        "WHERE login = ?";

        try (Connection connection = getConnection()) {
            return runner.query(
                    connection,
                    sql,
                    new ScalarHandler<>(),
                    login
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}