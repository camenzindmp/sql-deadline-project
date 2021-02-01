package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.AuthData;
import ru.netology.page.LoginPage;


public class LoginTest {

    @Test
    public void successLoginTest() {
        val loginPage = new LoginPage();
        val VerificationPage = loginPage.correctDataLogin(AuthData.getAuthInfo());
        val smsCode = AuthData.getVerificationCode();
        VerificationPage.verify(smsCode);
    }

    @Test
    public void failedLoginTest() {
        val loginPage = new LoginPage();
        loginPage.wrongPasswordLogin(AuthData.getWrongAuthInfo());
    }

    @Test
    // система не блокируется при троекратном вводе неверного пароля!
    public void successLoginAfterTripleFailed() {
        val loginPage = new LoginPage();
        loginPage.wrongPasswordLogin(AuthData.getWrongAuthInfo());
        loginPage.wrongPasswordLogin(AuthData.getWrongAuthInfo());
        loginPage.wrongPasswordLogin(AuthData.getWrongAuthInfo());
        val VerificationPage = loginPage.correctDataLogin(AuthData.getAuthInfo());
        val smsCode = AuthData.getVerificationCode();
        VerificationPage.verify(smsCode);
    }
}




