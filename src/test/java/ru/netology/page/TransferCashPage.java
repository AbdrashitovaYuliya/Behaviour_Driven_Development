package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferCashPage {
    private SelenideElement amountForTransfer = $("[data-test-id='amount'] .input__control");
    private SelenideElement cardNumberSelector = $("[data-test-id='from'] .input__control");
    private SelenideElement buttonTransfer = $("[data-test-id='action-transfer']");
    private SelenideElement error = $("[data-test-id=error-notification]");

    public DashBoardPage shouldCashInfo(DataHelper.CashTransfer cashTransfer, int amount) {
        amountForTransfer.setValue(String.valueOf(amount));
        cardNumberSelector.setValue(cashTransfer.getCardNumber());
        buttonTransfer.click();
        return new DashBoardPage();
    }

    public void noNegativeBalance(DataHelper.CashTransfer cashTransfer) {
        error.shouldHave(exactText(
                "На карте № " + cashTransfer.getCardNumber() + " недостаточно средств")).shouldBe(visible);
    }
}