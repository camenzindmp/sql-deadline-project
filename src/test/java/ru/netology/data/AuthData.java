package ru.netology.data;

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

    public static AuthInfo getCorrectAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getWrongAuthInfo() {
        return new AuthInfo("vasya", "badpassword");
    }

    @Value
    public static class VerificationCode {
        private String smsCode;
    }

    public static VerificationCode getWrongVerificationCode() {
        String smsCode = "123";
        return new VerificationCode(smsCode);
    }

    public static VerificationCode getCorrectVerificationCode() {
        QueryRunner runner = new QueryRunner();
        ScalarHandler<String> scalarHandler = new ScalarHandler<>();
        val dataSQL = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1;";
        try (val conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "admin", "pass")) {
            String smsCode = runner.query(conn, dataSQL, scalarHandler);
            return new VerificationCode(smsCode);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public static void clearTables() {
        QueryRunner runner = new QueryRunner();
        val clearCodes = "TRUNCATE auth_codes;";
        val clearCards = "TRUNCATE cards;";
        val clearUsers = "TRUNCATE users;";
        try (val conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "admin", "pass")) {
            runner.update(conn, clearCodes);
            runner.update(conn, clearCards);
            runner.update(conn, clearUsers);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

}


//
