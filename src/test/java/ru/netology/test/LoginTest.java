package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.AuthData;
import ru.netology.page.LoginPage;


public class LoginTest {

    @Test
    public void loginTest() {
        val loginPage = new LoginPage();
        val VerificationPage = loginPage.login(AuthData.getAuthInfo());
        val smsCode = AuthData.getVerificationCode();
        VerificationPage.verify(smsCode);
    }
}

