package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.AuthData;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;

public class LoginTest {

    @Test // Логин с верными данными и смс-кодом;
    public void successLoginTest() {
        val loginPage = new LoginPage();
        val VerificationPage = loginPage.correctDataLogin(AuthData.getCorrectAuthInfo());
        val smsCode = AuthData.getCorrectVerificationCode();
        VerificationPage.validVerify(smsCode);
    }

    @Test // Логин с некорректным паролем;
    public void failedLoginTest() {
        val loginPage = new LoginPage();
        loginPage.wrongPasswordLogin(AuthData.getWrongAuthInfo());
    }

    @Test // Логин с корректными данными но некорректным смс-кодом;
    public void loginWithWrongCode() {
        val loginPage = new LoginPage();
        val VerificationPage = loginPage.correctDataLogin(AuthData.getCorrectAuthInfo());
        val smsCode = AuthData.getWrongVerificationCode();
        VerificationPage.invalidVerify(smsCode);
    }

    @Test // Логин с троекратным вводом неправильного пароля;
    public void blockedLoginAfterTripleFailed() {
        val loginPage = new LoginPage();
        loginPage.tripleWrongPasswordLogin(AuthData.getWrongAuthInfo());
        loginPage.clearAllFields();
        val VerificationPage = loginPage.correctDataLogin(AuthData.getCorrectAuthInfo());
        val smsCode = AuthData.getCorrectVerificationCode();
        VerificationPage.blockedVerify(smsCode);
    }
}




