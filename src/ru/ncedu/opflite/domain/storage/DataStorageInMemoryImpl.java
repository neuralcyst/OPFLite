package ru.ncedu.opflite.domain.storage;

import ru.ncedu.opflite.domain.model.account.CustomerAccount;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataStorageInMemoryImpl implements DataStorageGateway {

    private static final DataStorageInMemoryImpl INSTANCE = new DataStorageInMemoryImpl();

    private final Map<String, StorableObject> memory = new HashMap<String, StorableObject>();

    public static DataStorageInMemoryImpl getInstance() {
        return INSTANCE;
    }

    private DataStorageInMemoryImpl() {
        // default constructor
    }

    @Override
    public synchronized  <H extends StorableObject> H findById(String id) {
        return (H) memory.get(id);
    }

    @Override
    public CustomerAccount findCustomerAccountByAccountNumber(String accountNumber) {
        for (Map.Entry<String, StorableObject> entry : memory.entrySet()) {
            if (entry.getValue() instanceof CustomerAccount) {
                CustomerAccount customerAccount = (CustomerAccount) entry.getValue();
                if (customerAccount.getAccountNumber().equals(accountNumber)) {
                    return customerAccount;
                }
            }
        }
        return null;
    }

    @Override
    public synchronized void save(StorableObject storableObject) {
        if (storableObject.getObjectId() == null) {
            storableObject.setObjectId(UUID.randomUUID().toString());
        }
        memory.put(storableObject.getObjectId(), storableObject);
    }
}
