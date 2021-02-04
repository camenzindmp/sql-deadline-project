package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.AuthData;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[class=input__control][type=text]");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");
    private SelenideElement invalidCodeError = $("[class=notification__content]");
    private SelenideElement blockingError = $("[data-test-id=error-notification]");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public void clearCodeField() {
        codeField.doubleClick().sendKeys(Keys.BACK_SPACE);
    }

    public DashboardPage validVerify(AuthData.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getSmsCode());
        verifyButton.click();
        return new DashboardPage();
    }

    public VerificationPage invalidVerify(AuthData.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getSmsCode());
        verifyButton.click();
        invalidCodeError.shouldBe(visible);
        return new VerificationPage();
    }

    public LoginPage blockedVerify(AuthData.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getSmsCode());
        verifyButton.click();
        blockingError.shouldBe(visible);
        return new LoginPage();
    }
}
