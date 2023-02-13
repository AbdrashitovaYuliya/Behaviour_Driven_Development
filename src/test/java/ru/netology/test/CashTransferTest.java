package ru.netology.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.netology.data.DataHelper;
import ru.netology.page.DashBoardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CashTransferTest {
    @BeforeEach
    void form() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        Configuration.browserCapabilities = options;
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        loginPage.validLogin(authInfo).validVerify(verificationCode);
    }

    @Test
    void shouldTransferCashFromFirstCard() {

        int amount = 100;
        var cardInfo = DataHelper.getFirstCardNumber();

        var dashboard = new DashBoardPage();
        int balanceFirstCard = dashboard.getCardBalance("0");
        int balanceSecondCard = dashboard.getCardBalance("1");

        dashboard.changeCard(1).shouldCashInfo(cardInfo, amount);
        int finalBalanceFirstCard = dashboard.getCardBalance("0");
        int finalBalanceSecondCard = dashboard.getCardBalance("1");

        assertTrue(finalBalanceFirstCard > 0 && finalBalanceSecondCard > 0);

        assertEquals(finalBalanceFirstCard, (balanceFirstCard - amount));
        assertEquals(finalBalanceSecondCard, (balanceSecondCard + amount));
    }

    @Test
    void shouldTransferCashFromSecondCard() {

        int amount = 1000;
        var cardInfo = DataHelper.getSecondCardNumber();


        var dashboard = new DashBoardPage();
        int finalBalanceFirstCard = dashboard.getCardBalance("1");
        int finalBalanceSecondCard = dashboard.getCardBalance("0");

        dashboard.changeCard(0).shouldCashInfo(cardInfo, amount);
        int balanceFirstCard = dashboard.getCardBalance("1");
        int balanceSecondCard = dashboard.getCardBalance("0");

        assertTrue(finalBalanceFirstCard > 0 && finalBalanceSecondCard > 0);

        assertEquals(finalBalanceFirstCard, balanceFirstCard + amount);
        assertEquals(finalBalanceSecondCard, balanceSecondCard - amount);
    }

    @Test
    void shouldTransferNegativeBalance() {

        int amount = 20000;
        var cardInfo = DataHelper.getFirstCardNumber();

        var dashboard = new DashBoardPage();

        dashboard.changeCard(1).shouldCashInfo(cardInfo, amount);
        int finalBalanceFirstCard = dashboard.getCardBalance("0");
        int finalBalanceSecondCard = dashboard.getCardBalance("1");

        assertTrue(finalBalanceFirstCard > 0 && finalBalanceSecondCard > 0);
    }
}