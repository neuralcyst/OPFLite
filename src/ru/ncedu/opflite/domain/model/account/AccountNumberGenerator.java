package ru.ncedu.opflite.domain.model.account;

public class AccountNumberGenerator {

    private static int COUNTER;

    private AccountNumberGenerator() {
        // class instance doesn't make sense
    }

    public synchronized static String generateNewAccountNumber() {
        COUNTER++;
        return Integer.toString(COUNTER);
    }

}
