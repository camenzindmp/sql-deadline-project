package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.AuthData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    public LoginPage() {
        open("http://localhost:9999");

    }

    private SelenideElement loginField = $("[class='input__control'][type=text]");
    private SelenideElement passwordField = $("[class='input__control'][type=password]");
    private SelenideElement loginButton = $("[type=button] [class='button__content']");
    private SelenideElement errorNotification = $("[data-test-id=error-notification]");

    public void loginBlocked() {
        errorNotification.shouldBe(visible).shouldHave(text("Ошибка! Превышено количество попыток ввода кода"));
    }

    public VerificationPage correctDataLogin(AuthData.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public LoginPage wrongPasswordLogin(AuthData.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        errorNotification.shouldBe(Condition.visible);
        return new LoginPage();
    }

    public void tripleWrongPasswordLogin(AuthData.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        errorNotification.shouldBe(Condition.visible);
        loginButton.click();
        errorNotification.shouldBe(Condition.visible);
        loginButton.click();
        errorNotification.shouldBe(Condition.visible);
    }
}