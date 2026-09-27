package ru.netology.data;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Database operations used by the UI tests. */
public final class DbHelper {
    private static final String URL = System.getenv().getOrDefault(
            "DB_URL", "jdbc:mysql://localhost:3306/app?useSSL=false&allowPublicKeyRetrieval=true"
    );
    private static final String USER = System.getenv().getOrDefault("DB_USER", "app");
    private static final String PASSWORD = System.getenv().getOrDefault("DB_PASS", "pass");

    private DbHelper() {
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static String getVerificationCode(String login) {
        String sql = "SELECT ac.code "
                + "FROM auth_codes ac "
                + "JOIN users u ON u.id = ac.user_id "
                + "WHERE u.login = ? "
                + "ORDER BY ac.created DESC "
                + "LIMIT 1";

        try (Connection connection = getConnection()) {
            String code = new QueryRunner().query(
                    connection,
                    sql,
                    new ScalarHandler<>(),
                    login
            );
            if (code == null) {
                throw new IllegalStateException("No verification code found for user " + login);
            }
            return code;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to read the verification code for " + login, e);
        }
    }

    /**
     * Removes test/application data in foreign-key-safe order. The users table
     * must be cleared last because cards and auth_codes reference it.
     */
    public static void cleanDatabase() {
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            QueryRunner runner = new QueryRunner();
            try {
                runner.update(connection, "DELETE FROM card_transactions");
                runner.update(connection, "DELETE FROM auth_codes");
                runner.update(connection, "DELETE FROM cards");
                runner.update(connection, "DELETE FROM users");
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to clean test data", e);
        }
    }
}
