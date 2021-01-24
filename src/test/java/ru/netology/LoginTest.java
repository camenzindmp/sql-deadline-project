package ru.netology;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.AuthData;
import ru.netology.LoginPage;

import java.sql.SQLException;


public class LoginTest {

    @Test
    public void loginTest() throws SQLException {
        val loginPage = new LoginPage();
        loginPage.openLoginPage();
        val VerificationPage = loginPage.login(AuthData.getAuthInfo());
        val smsCode = AuthData.getVerificationCode();
        VerificationPage.verify(smsCode);
    }
}

