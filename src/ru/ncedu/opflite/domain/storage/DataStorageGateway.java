package ru.ncedu.opflite.domain.storage;

import ru.ncedu.opflite.domain.model.account.CustomerAccount;

public interface DataStorageGateway {
    <H extends StorableObject> H findById(String id);
    CustomerAccount findCustomerAccountByAccountNumber(String accountNumber);
    void save(StorableObject storableObject);
}
