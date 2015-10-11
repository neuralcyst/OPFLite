package ru.ncedu.opflite.integration;

import ru.ncedu.opflite.domain.businesslogic.CustomerAccountFacade;
import ru.ncedu.opflite.domain.model.account.CustomerAccount;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

public final class OPFLiteFacade {

    private OPFLiteFacade() {
        // class instance doesn't make sense
    }

    public static String createCustomerAccount(String type, String firstName, String lastName) throws ContractViolationException {
        try {
            CustomerAccount customerAccount = CustomerAccountFacade.createCustomerAccount(type, firstName, lastName);
            return customerAccount.getAccountNumber();
        } catch (IllegalArgumentException e) {
            throw new ContractViolationException(e);
        }
    }

    public static void updateCustomerAccount(String accountNumber, String firstName, String lastName) throws ContractViolationException {
        try {
            CustomerAccountFacade.updateCustomerAccount(accountNumber, firstName, lastName);
        } catch (IllegalArgumentException e) {
            throw new ContractViolationException(e);
        }
    }

    public static Map<String, String> getCustomerAccountInfo(String accountNumber) throws ContractViolationException {
        try {
            CustomerAccount customerAccount = CustomerAccountFacade.findCustomerAccount(accountNumber);
            Map<String, String> response = new HashMap<String, String>();
            response.put("ACCOUNT_NUMBER", customerAccount.getAccountNumber());
            response.put("TYPE", customerAccount.getType().getContractId());
            response.put("FIRST_NAME", customerAccount.getFirstName());
            response.put("LAST_NAME", customerAccount.getLastName());
            return response;
        } catch (IllegalArgumentException e) {
            throw new ContractViolationException(e);
        }
    }

    public static void addInternetService(String accountNumber, String speed, String modemSerialNumber) throws ContractViolationException {
        throw new NotImplementedException();
    }

    public static void addTVService(String accountNumber, String channelsPackage, String cableTerminalSerialNumber) throws ContractViolationException {
        throw new NotImplementedException();
    }

    public static void suspendInternetService(String accountNumber) throws ContractViolationException {
        throw new NotImplementedException();
    }

    public static void suspendTVService(String accountNumber) throws ContractViolationException {
        throw new NotImplementedException();
    }

    public static void resumeInternetService(String accountNumber) throws ContractViolationException {
        throw new NotImplementedException();
    }

    public static void resumeTVService(String accountNumber) throws ContractViolationException {
        throw new NotImplementedException();
    }

    public static void disconnectInternetService(String accountNumber) throws ContractViolationException {
        throw new NotImplementedException();
    }

    public static void disconnectTVService(String accountNumber) throws ContractViolationException {
        throw new NotImplementedException();
    }

    public static Map<String, Map<String, String>> getAllServices(String accountNumber) throws ContractViolationException {
        throw new NotImplementedException();
    }

}
