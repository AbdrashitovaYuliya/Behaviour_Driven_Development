package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeVerification = $("[data-test-id='code'] input");
    private SelenideElement buttonVerification = $("[data-test-id='action-verify']");

    public DashBoardPage validVerify(DataHelper.VerificationCode verificationCode) {
        codeVerification.setValue(verificationCode.getCode());
        buttonVerification.click();
        return new DashBoardPage();
    }
}