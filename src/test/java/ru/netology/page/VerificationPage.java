package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.AuthData;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[class=input__control][type=text]");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public VerificationPage() {
        codeField.shouldBe(Condition.visible);
    }

    public DashboardPage verify(AuthData.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getSmsCode());
        verifyButton.click();
        return new DashboardPage();
    }
}
