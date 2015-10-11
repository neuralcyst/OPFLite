package ru.ncedu.opflite.functionaltests;

import ru.ncedu.opflite.integration.ContractViolationException;
import ru.ncedu.opflite.integration.OPFLiteFacade;

import java.util.Map;

import static ru.ncedu.opflite.util.StringUtils.isNotEmpty;

public class CustomerAccountTests {

    static final String RESIDENTIAL = "R";
    static final String ORGANIZATION = "O";

    static final String ACCOUNT_NUMBER_FIELD = "ACCOUNT_NUMBER";
    static final String TYPE_FIELD = "TYPE";
    static final String FIRST_NAME_FIELD = "FIRST_NAME";
    static final String LAST_NAME_FIELD = "LAST_NAME";

    static final String WRONG_ARGUMENT = "";

    static final String FIRST_NAME = "Someone";
    static final String LAST_NAME = "Someoneovich";
    static final String ORGANIZATION_NAME = "Something Inc";
    static final String NEW_FIRST_NAME = "New Someone";
    static final String NEW_LAST_NAME = "New Someoneovich";
    static final String NEW_ORGANIZATION_NAME = "Something Inc";

    static final String WRONG_ACCOUNT_NUMBER = "123wrongnumber";

    public static void runTests() {
        residentialSunnyDayTest();
        organizationSunnyDayTest();
        createWrongTypeTest();
        searchByWrongAccountNumberTest();
        organizationNamingViolationTest();
        residentialNamingViolationTest();
    }

    private static void residentialSunnyDayTest() {
        try {
            String accountNumber = OPFLiteFacade.createCustomerAccount(RESIDENTIAL, FIRST_NAME, LAST_NAME);
            Map<String, String> result = OPFLiteFacade.getCustomerAccountInfo(accountNumber);
            if (!accountNumber.equals(result.get(ACCOUNT_NUMBER_FIELD))
                    || !RESIDENTIAL.equals(result.get(TYPE_FIELD))
                    || !FIRST_NAME.equals(result.get(FIRST_NAME_FIELD))
                    || !LAST_NAME.equals(result.get(LAST_NAME_FIELD))) {
                throw new TestFailedException("residentialSunnyDayTest");
            }
            OPFLiteFacade.updateCustomerAccount(accountNumber, NEW_FIRST_NAME, NEW_LAST_NAME);
            result = OPFLiteFacade.getCustomerAccountInfo(accountNumber);
            if (!accountNumber.equals(result.get(ACCOUNT_NUMBER_FIELD))
                    || !RESIDENTIAL.equals(result.get(TYPE_FIELD))
                    || !NEW_FIRST_NAME.equals(result.get(FIRST_NAME_FIELD))
                    || !NEW_LAST_NAME.equals(result.get(LAST_NAME_FIELD))) {
                throw new TestFailedException("residentialSunnyDayTest");
            }
        } catch (TestFailedException e) {
            throw e;
        } catch (Exception e) {
            throw new TestFailedException("residentialSunnyDayTest", e);
        }
    }

    private static void organizationSunnyDayTest() {
        try {
            String accountNumber = OPFLiteFacade.createCustomerAccount(ORGANIZATION, ORGANIZATION_NAME, null);
            Map<String, String> result = OPFLiteFacade.getCustomerAccountInfo(accountNumber);
            if (!accountNumber.equals(result.get(ACCOUNT_NUMBER_FIELD))
                    || !ORGANIZATION.equals(result.get(TYPE_FIELD))
                    || !ORGANIZATION_NAME.equals(result.get(FIRST_NAME_FIELD))
                    || isNotEmpty(result.get(LAST_NAME_FIELD))) {
                throw new TestFailedException("organizationSunnyDayTest");
            }
            OPFLiteFacade.updateCustomerAccount(accountNumber, NEW_ORGANIZATION_NAME, null);
            result = OPFLiteFacade.getCustomerAccountInfo(accountNumber);
            if (!accountNumber.equals(result.get(ACCOUNT_NUMBER_FIELD))
                    || !ORGANIZATION.equals(result.get(TYPE_FIELD))
                    || !NEW_ORGANIZATION_NAME.equals(result.get(FIRST_NAME_FIELD))
                    || isNotEmpty(result.get(LAST_NAME_FIELD))) {
                throw new TestFailedException("organizationSunnyDayTest");
            }
        } catch (TestFailedException e) {
            throw e;
        } catch (Exception e) {
            throw new TestFailedException("organizationSunnyDayTest", e);
        }
    }

    private static void createWrongTypeTest() {
        try {
            OPFLiteFacade.createCustomerAccount(WRONG_ARGUMENT, ORGANIZATION_NAME, null);
        } catch (ContractViolationException e) {
            return;
        }
        throw new TestFailedException("createWrongTypeTest");
    }

    private static void searchByWrongAccountNumberTest() {
        try {
            OPFLiteFacade.getCustomerAccountInfo(WRONG_ACCOUNT_NUMBER);
        } catch (ContractViolationException e) {
            return;
        }
        throw new TestFailedException("createWrongTypeTest");
    }

    private static void organizationNamingViolationTest() {
        try {
            OPFLiteFacade.createCustomerAccount(ORGANIZATION, FIRST_NAME, LAST_NAME);
        } catch (ContractViolationException e) {
            return;
        }
        throw new TestFailedException("createWrongTypeTest");
    }

    private static void residentialNamingViolationTest() {
        try {
            OPFLiteFacade.createCustomerAccount(RESIDENTIAL, FIRST_NAME, null);
        } catch (ContractViolationException e) {
            return;
        }
        throw new TestFailedException("createWrongTypeTest");
    }
}
