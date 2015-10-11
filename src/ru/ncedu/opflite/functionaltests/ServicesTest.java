package ru.ncedu.opflite.functionaltests;

import ru.ncedu.opflite.integration.ContractViolationException;
import ru.ncedu.opflite.integration.OPFLiteFacade;

import java.util.Map;

public class ServicesTest {

    static final String INTERNET_SPEED = "100";
    static final String MODEM_SERIAL_NUMBER = "123-87686-33";
    static final String CHANNELS_PACKAGE = "Detectives";
    static final String CABLE_TERMINAL_SERIAL_NUMBER = "DPT-333-23";
    static final String ACTIVE_STATUS = "ACTIVE";
    static final String WRONG_ARGUMENT = "";

    static final String INTERNET_SERVICE = "INTERNET";
    static final String TV_SERVICE = "TV";
    static final String STATUS_FIELD = "STATUS";
    static final String INTERNET_SPEED_FIELD = "INTERNET_SPEED";
    static final String MODEM_SERIAL_NUMBER_FIELD = "MODEM_SERIAL_NUMBER";
    static final String CHANNELS_PACKAGE_FIELD = "CHANNELS_PACKAGE";
    static final String CABLE_TERMINAL_SERIAL_NUMBER_FIELD = "CABLE_TERMINAL_SERIAL_NUMBER";

    public static void runTests() {
        internetServiceSunnyDayScenarioTest();
        tvServiceSunnyDayScenarioTest();
        wrongInternetArgumentsTest();
        wrongTVArgumentsTest();
        duplicateInternetServiceErrorTest();
        duplicateTVServiceErrorTest();
        wrongInternetServiceFlowTest();
        wrongTVServiceFlowTest();
        getAllServicesTest();
    }

    private static void internetServiceSunnyDayScenarioTest() {
        try {
            String accountNumber = createCustomerAccount();
            OPFLiteFacade.addInternetService(accountNumber, INTERNET_SPEED, MODEM_SERIAL_NUMBER);
            OPFLiteFacade.suspendInternetService(accountNumber);
            OPFLiteFacade.resumeInternetService(accountNumber);
            OPFLiteFacade.disconnectInternetService(accountNumber);
        } catch (Exception e) {
            throw new TestFailedException("internetServiceSunnyDayScenarioTest", e);
        }
    }

    private static void tvServiceSunnyDayScenarioTest() {
        try {
            String accountNumber = createCustomerAccount();
            OPFLiteFacade.addTVService(accountNumber, CHANNELS_PACKAGE, CABLE_TERMINAL_SERIAL_NUMBER);
            OPFLiteFacade.suspendTVService(accountNumber);
            OPFLiteFacade.resumeTVService(accountNumber);
            OPFLiteFacade.disconnectTVService(accountNumber);
        } catch (Exception e) {
            throw new TestFailedException("tvServiceSunnyDayScenarioTest", e);
        }
    }

    private static void wrongInternetArgumentsTest() {
        try {
            String accountNumber = createCustomerAccount();
            OPFLiteFacade.addInternetService(accountNumber, WRONG_ARGUMENT, WRONG_ARGUMENT);
        } catch (ContractViolationException e) {
            return;
        } catch (Exception e) {
            throw new TestFailedException("wrongInternetArgumentsTest", e);
        }
        throw new TestFailedException("wrongInternetArgumentsTest");
    }

    private static void wrongTVArgumentsTest() {
        try {
            String accountNumber = createCustomerAccount();
            OPFLiteFacade.addTVService(accountNumber, WRONG_ARGUMENT, WRONG_ARGUMENT);
        } catch (ContractViolationException e) {
            return;
        } catch (Exception e) {
            throw new TestFailedException("wrongTVArgumentsTest", e);
        }
        throw new TestFailedException("wrongTVArgumentsTest");
    }

    private static void duplicateInternetServiceErrorTest() {
        String accountNumber;
        try {
            accountNumber = createCustomerAccount();
            OPFLiteFacade.addInternetService(accountNumber, INTERNET_SPEED, MODEM_SERIAL_NUMBER);
        } catch (Exception e) {
            throw new TestFailedException("duplicateInternetServiceErrorTest", e);
        }
        try {
            OPFLiteFacade.addInternetService(accountNumber, INTERNET_SPEED, MODEM_SERIAL_NUMBER);
        } catch (ContractViolationException e) {
            return;
        } catch (Exception e) {
            throw new TestFailedException("duplicateInternetServiceErrorTest", e);
        }
        throw new TestFailedException("duplicateInternetServiceErrorTest");
    }

    private static void duplicateTVServiceErrorTest() {
        String accountNumber;
        try {
            accountNumber = createCustomerAccount();
            OPFLiteFacade.addTVService(accountNumber, CHANNELS_PACKAGE, CABLE_TERMINAL_SERIAL_NUMBER);
        } catch (Exception e) {
            throw new TestFailedException("duplicateTVServiceErrorTest", e);
        }
        try {
            OPFLiteFacade.addTVService(accountNumber, CHANNELS_PACKAGE, CABLE_TERMINAL_SERIAL_NUMBER);
        } catch (ContractViolationException e) {
            return;
        } catch (Exception e) {
            throw new TestFailedException("duplicateTVServiceErrorTest", e);
        }
        throw new TestFailedException("duplicateTVServiceErrorTest");
    }

    private static void wrongInternetServiceFlowTest() {
        String accountNumber;

        try {
            accountNumber = createCustomerAccount();
            OPFLiteFacade.addInternetService(accountNumber, INTERNET_SPEED, MODEM_SERIAL_NUMBER);
        } catch (Exception e) {
            throw new TestFailedException("wrongInternetServiceFlowTest", e);
        }

        try {
            OPFLiteFacade.resumeInternetService(accountNumber);
        } catch (ContractViolationException e) {
            // expected exception
        } catch (Exception e) {
            throw new TestFailedException("wrongInternetServiceFlowTest", e);
        }

        try {
            OPFLiteFacade.suspendInternetService(accountNumber);
        } catch (Exception e) {
            throw new TestFailedException("wrongInternetServiceFlowTest", e);
        }

        try {
            OPFLiteFacade.suspendInternetService(accountNumber);
        } catch (ContractViolationException e) {
            // expected exception
        } catch (Exception e) {
            throw new TestFailedException("wrongInternetServiceFlowTest", e);
        }

        try {
            OPFLiteFacade.disconnectInternetService(accountNumber);
        } catch (Exception e) {
            throw new TestFailedException("wrongInternetServiceFlowTest", e);
        }

        try {
            OPFLiteFacade.disconnectInternetService(accountNumber);
        } catch (ContractViolationException e) {
            return;
        } catch (Exception e) {
            throw new TestFailedException("wrongInternetServiceFlowTest", e);
        }

        throw new TestFailedException("wrongInternetServiceFlowTest");
    }

    private static void wrongTVServiceFlowTest() {
        String accountNumber;

        try {
            accountNumber = createCustomerAccount();
            OPFLiteFacade.addTVService(accountNumber, CHANNELS_PACKAGE, CABLE_TERMINAL_SERIAL_NUMBER);
        } catch (Exception e) {
            throw new TestFailedException("wrongTVServiceFlowTest", e);
        }

        try {
            OPFLiteFacade.resumeTVService(accountNumber);
        } catch (ContractViolationException e) {
            // expected exception
        } catch (Exception e) {
            throw new TestFailedException("wrongTVServiceFlowTest", e);
        }

        try {
            OPFLiteFacade.suspendTVService(accountNumber);
        } catch (Exception e) {
            throw new TestFailedException("wrongTVServiceFlowTest", e);
        }

        try {
            OPFLiteFacade.suspendTVService(accountNumber);
        } catch (ContractViolationException e) {
            // expected exception
        } catch (Exception e) {
            throw new TestFailedException("wrongTVServiceFlowTest", e);
        }

        try {
            OPFLiteFacade.disconnectTVService(accountNumber);
        } catch (Exception e) {
            throw new TestFailedException("wrongTVServiceFlowTest", e);
        }

        try {
            OPFLiteFacade.disconnectTVService(accountNumber);
        } catch (ContractViolationException e) {
            return;
        } catch (Exception e) {
            throw new TestFailedException("wrongTVServiceFlowTest", e);
        }

        throw new TestFailedException("wrongTVServiceFlowTest");
    }

    private static void getAllServicesTest() {

        Map<String, Map<String, String>> services;

        try {
            String accountNumber = createCustomerAccount();
            OPFLiteFacade.addInternetService(accountNumber, INTERNET_SPEED, MODEM_SERIAL_NUMBER);
            OPFLiteFacade.addTVService(accountNumber, CHANNELS_PACKAGE, CABLE_TERMINAL_SERIAL_NUMBER);
            services = OPFLiteFacade.getAllServices(accountNumber);
        } catch (Exception e) {
            throw new TestFailedException("getAllServicesTest", e);
        }

        if (services == null) {
            throw new TestFailedException("getAllServicesTest");
        }

        Map<String, String> internetParams = services.get(INTERNET_SERVICE);
        Map<String, String> tvParams = services.get(TV_SERVICE);

        if (internetParams == null || tvParams == null) {
            throw new TestFailedException("getAllServicesTest");
        }

        if (!ACTIVE_STATUS.equals(internetParams.get(STATUS_FIELD))
                || !INTERNET_SPEED.equals(internetParams.get(INTERNET_SPEED_FIELD))
                || !MODEM_SERIAL_NUMBER.equals(internetParams.get(MODEM_SERIAL_NUMBER_FIELD))
                || !ACTIVE_STATUS.equals(tvParams.get(STATUS_FIELD))
                || !CHANNELS_PACKAGE.equals(tvParams.get(CHANNELS_PACKAGE_FIELD))
                || !CABLE_TERMINAL_SERIAL_NUMBER.equals(tvParams.get(CABLE_TERMINAL_SERIAL_NUMBER_FIELD))) {
            throw new TestFailedException("getAllServicesTest");
        }
    }

    private static String createCustomerAccount() throws ContractViolationException {
        return OPFLiteFacade.createCustomerAccount(CustomerAccountTests.RESIDENTIAL,
                CustomerAccountTests.FIRST_NAME, CustomerAccountTests.LAST_NAME);
    }



}
