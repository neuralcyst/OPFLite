package ru.ncedu.opflite.domain.managers;

import ru.ncedu.opflite.domain.model.account.AccountNumberGenerator;
import ru.ncedu.opflite.domain.model.account.CustomerAccount;
import ru.ncedu.opflite.domain.model.account.CustomerType;
import ru.ncedu.opflite.domain.storage.DataStorageGateway;
import ru.ncedu.opflite.util.Context;

import static ru.ncedu.opflite.util.StringUtils.*;

public class CustomerAccountFacade {

    public static CustomerAccount createCustomerAccount(String type, String firstName, String lastName) {
        CustomerType customerType = CustomerType.getCustomerTypeByContractId(type);

        if (customerType == null) {
            throw new IllegalArgumentException("Wrong customer type");
        }

        checkArgumentsByCustomerType(customerType, firstName, lastName);

        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setType(customerType);
        customerAccount.setFirstName(firstName);
        customerAccount.setLastName(lastName);
        customerAccount.setAccountNumber(AccountNumberGenerator.generateNewAccountNumber());
        Context.getDataStorageGateway().save(customerAccount);
        return customerAccount;
    }

    public static void updateCustomerAccount(String accountNumber, String firstName, String lastName) {
        if (isEmpty(accountNumber)) {
            throw new IllegalArgumentException("Account number is empty");
        }

        DataStorageGateway gateway = Context.getDataStorageGateway();
        CustomerAccount customerAccount = gateway.findCustomerAccountByAccountNumber(accountNumber);

        checkArgumentsByCustomerType(customerAccount.getType(), firstName, lastName);

        customerAccount.setFirstName(firstName);
        customerAccount.setLastName(lastName);
        gateway.save(customerAccount);
    }

    public static CustomerAccount findCustomerAccount(String accountNumber) {
        if (isEmpty(accountNumber)) {
            throw new IllegalArgumentException("Account number is empty");
        }
        CustomerAccount customerAccount = Context.getDataStorageGateway().findCustomerAccountByAccountNumber(accountNumber);
        if (customerAccount == null) {
            throw new IllegalArgumentException("There is no customer account with account number: " + accountNumber);
        }
        return customerAccount;
    }

    private static void checkArgumentsByCustomerType(CustomerType customerType, String firstName, String lastName) {
        if (CustomerType.ORGANIZATION.equals(customerType)
                && (isEmpty(firstName) || isNotEmpty(lastName))) {
            throw new IllegalArgumentException("Wrong name parameters");
        }
        if (CustomerType.RESIDENTIAL.equals(customerType)
                && (isEmpty(firstName) || isEmpty(lastName))) {
            throw new IllegalArgumentException("Wrong name parameters");
        }
    }

}
