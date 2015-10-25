package ru.ncedu.opflite.integration;

import ru.ncedu.opflite.domain.businesslogic.CustomerAccountFacade;
import ru.ncedu.opflite.domain.model.account.CustomerAccount;
import ru.ncedu.opflite.domain.model.account.InternetService;
import ru.ncedu.opflite.domain.model.account.ServiceStatus;
import ru.ncedu.opflite.domain.model.account.TVService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

import static ru.ncedu.opflite.util.StringUtils.isEmpty;

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
        try {

            CustomerAccount customerAccount = CustomerAccountFacade.findCustomerAccount(accountNumber);

            checkArguments(speed, modemSerialNumber);
            checkDuplicates(customerAccount, "INTERNET");

            InternetService internetService = new InternetService();
            ServiceStatus status = ServiceStatus.ACTIVE;
            internetService.setServiceStatus(status);
            internetService.setInternetSpeed(speed);
            internetService.setModemSerialNumber(modemSerialNumber);
            customerAccount.addService("INTERNET", internetService);
        } catch (IllegalArgumentException e) {
            throw new ContractViolationException(e);
        }
    }

    public static void addTVService(String accountNumber, String channelsPackage, String cableTerminalSerialNumber) throws ContractViolationException {
        try {
            CustomerAccount customerAccount = CustomerAccountFacade.findCustomerAccount(accountNumber);

            checkArguments(channelsPackage, cableTerminalSerialNumber);
            checkDuplicates(customerAccount, "TV");

            TVService tvService = new TVService();
            ServiceStatus status = ServiceStatus.ACTIVE;
            tvService.setServiceStatus(status);
            tvService.setChannelPackage(channelsPackage);
            tvService.setCableTerminalSerialNumber(cableTerminalSerialNumber);
            customerAccount.addService("TV", tvService);
        } catch (IllegalArgumentException e) {
            throw new ContractViolationException(e);
        }
    }

    public static void suspendInternetService(String accountNumber) throws ContractViolationException {
        try {
            CustomerAccount customerAccount = CustomerAccountFacade.findCustomerAccount(accountNumber);
            InternetService internetService = (InternetService) customerAccount.getService("INTERNET");
            ServiceStatus status = ServiceStatus.INACTIVE;

            checkServiceStatus(internetService.getServiceStatus(),status);

            internetService.setServiceStatus(status);
        } catch (IllegalArgumentException e) {
            throw new ContractViolationException(e);
        }
    }

    public static void suspendTVService(String accountNumber) throws ContractViolationException {
        try {
            CustomerAccount customerAccount = CustomerAccountFacade.findCustomerAccount(accountNumber);
            TVService tvService = (TVService) customerAccount.getService("TV");
            ServiceStatus status = ServiceStatus.INACTIVE;

            checkServiceStatus(tvService.getServiceStatus(),status);

            tvService.setServiceStatus(status);
        } catch (IllegalArgumentException e) {
            throw new ContractViolationException(e);
        }
    }

    public static void resumeInternetService(String accountNumber) throws ContractViolationException {
        try {
            CustomerAccount customerAccount = CustomerAccountFacade.findCustomerAccount(accountNumber);
            InternetService internetService = (InternetService) customerAccount.getService("INTERNET");
            ServiceStatus status = ServiceStatus.ACTIVE;

            checkServiceStatus(internetService.getServiceStatus(),status);

            internetService.setServiceStatus(status);

        } catch (IllegalArgumentException e) {
            throw new ContractViolationException(e);
        }
    }

    public static void resumeTVService(String accountNumber) throws ContractViolationException {
        try {
            CustomerAccount customerAccount = CustomerAccountFacade.findCustomerAccount(accountNumber);
            TVService tvService = (TVService) customerAccount.getService("TV");
            ServiceStatus status = ServiceStatus.ACTIVE;

            checkServiceStatus(tvService.getServiceStatus(),status);

            tvService.setServiceStatus(status);
        } catch (IllegalArgumentException e) {
            throw new ContractViolationException(e);
        }
    }

    public static void disconnectInternetService(String accountNumber) throws ContractViolationException {
        try {
            CustomerAccount customerAccount = CustomerAccountFacade.findCustomerAccount(accountNumber);

            checkServiceAvailability(customerAccount, "INTERNET");

            customerAccount.deleteService("INTERNET");
        } catch (IllegalArgumentException e) {
            throw new ContractViolationException(e);
        }
    }

    public static void disconnectTVService(String accountNumber) throws ContractViolationException {
        try {
            CustomerAccount customerAccount = CustomerAccountFacade.findCustomerAccount(accountNumber);

            checkServiceAvailability(customerAccount, "TV");

            customerAccount.deleteService("TV");
        } catch (IllegalArgumentException e) {
            throw new ContractViolationException(e);
        }
    }

    public static Map<String, Map<String, String>> getAllServices(String accountNumber) throws ContractViolationException {
        try {
            CustomerAccount customerAccount = CustomerAccountFacade.findCustomerAccount(accountNumber);



            Map<String, Map<String, String>> allServices = new HashMap<>();
            Map<String, String> internetParams = new HashMap<>();
            Map<String, String> tvParams = new HashMap<>();

            InternetService internetService = (InternetService) customerAccount.getService("INTERNET");
            ServiceStatus internetStatus = internetService.getServiceStatus();
            internetParams.put("INTERNET_SPEED", internetService.getInternetSpeed());
            internetParams.put("MODEM_SERIAL_NUMBER", internetService.getModemSerialNumber());
            internetParams.put("STATUS", internetStatus.toString());

            TVService tvService = (TVService) customerAccount.getService("TV");
            ServiceStatus tvStatus = tvService.getServiceStatus();
            tvParams.put("STATUS", tvStatus.toString());
            tvParams.put("CABLE_TERMINAL_SERIAL_NUMBER", tvService.getCableTerminalSerialNumber());
            tvParams.put("CHANNELS_PACKAGE", tvService.getChannelPackage());

            allServices.put("INTERNET", internetParams);
            allServices.put("TV", tvParams);
            return allServices;

        } catch (IllegalArgumentException e) {
            throw new ContractViolationException(e);
        }
    }

    private static void checkArguments(String arg1, String arg2) {
        if ((isEmpty(arg1) || isEmpty(arg2))) {
            throw new IllegalArgumentException("Wrong name parameters");
        }
    }
    private static void checkDuplicates(CustomerAccount customerAccount, String serviceName){
        if(existsInServices(customerAccount, serviceName))
            throw new IllegalArgumentException("Service has already been  added");
    }
    private static void checkServiceStatus(ServiceStatus currentStatus, ServiceStatus status){
        if(currentStatus.equals(status))
            throw new IllegalArgumentException("Wrong status");
    }
    private static void checkServiceAvailability(CustomerAccount customerAccount, String serviceName){
        if(!(existsInServices(customerAccount, serviceName)))
            throw new IllegalArgumentException("Service hasn't  been  added");
    }
    private static boolean existsInServices(CustomerAccount customerAccount, String serviceName){
        return customerAccount.getService(serviceName) != null;
    }

}