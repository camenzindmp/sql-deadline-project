package ru.netology;

import lombok.Value;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

public class AuthData {
    private AuthData() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String smsCode;
    }

    public static getVerificationCode(AuthInfo authInfo) throws SQLException {
        QueryRunner runner = new QueryRunner();
        ScalarHandler<Long> scalarHandler = new ScalarHandler<>();
        val dataSQL = "SELECT code * FROM auth_codes;";


        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://172.23.0.1:3306/mysql", "admin", "pass"
                );
        ) {
            val code = runner.query(conn, dataSQL, scalarHandler);
            return code;
        }

    }
}

