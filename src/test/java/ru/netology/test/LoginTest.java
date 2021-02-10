package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.AuthData;
import ru.netology.page.LoginPage;

public class LoginTest {

    @Test // Логин с верными данными и смс-кодом;
    public void successLoginTest() {
        val loginPage = new LoginPage();
        val verificationPage = loginPage.correctDataLogin(AuthData.getCorrectAuthInfo());
        val smsCode = AuthData.getCorrectVerificationCode();
        verificationPage.validVerify(smsCode);
    }

    @Test // Логин с некорректным паролем;
    public void failedLoginTest() {
        val loginPage = new LoginPage();
        loginPage.wrongPasswordLogin(AuthData.getWrongAuthInfo());
    }

    @Test // Логин с корректными данными но некорректным смс-кодом;
    public void loginWithWrongCode() {
        val loginPage = new LoginPage();
        val verificationPage = loginPage.correctDataLogin(AuthData.getCorrectAuthInfo());
        val smsCode = AuthData.getWrongVerificationCode();
        verificationPage.invalidVerify(smsCode);
    }

    @Test // Логин с троекратным вводом неправильного пароля;
    public void blockedLoginAfterTripleFailed() {
        val loginPage = new LoginPage();
        loginPage.tripleWrongPasswordLogin(AuthData.getWrongAuthInfo());
        loginPage.clearAllFields();
        val verificationPage = loginPage.correctDataLogin(AuthData.getCorrectAuthInfo());
        val smsCode = AuthData.getCorrectVerificationCode();
        verificationPage.blockedVerify(smsCode);
    }

    @AfterAll
    public static void clear() {
        AuthData.clearTables();
    }
}




