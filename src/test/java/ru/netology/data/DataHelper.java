package ru.netology.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class CashTransfer {
        private String cardNumber;
        private String id;
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static VerificationCode getVerificationCodeFor(DataHelper.AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    public static CashTransfer getFirstCardNumber() {
        return new CashTransfer("5559 0000 0000 0001", "0");

    }

    public static CashTransfer getSecondCardNumber() {
        return new CashTransfer("5559 0000 0000 0002", "1");

    }
}