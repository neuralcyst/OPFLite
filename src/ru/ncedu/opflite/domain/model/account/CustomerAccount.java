package ru.ncedu.opflite.domain.model.account;

import ru.ncedu.opflite.domain.storage.StorableObject;

import java.util.HashMap;
import java.util.Map;

public class CustomerAccount implements StorableObject {

    private String objectId;

    private String firstName;

    private String lastName;

    private String accountNumber;

    private CustomerType type;

    private Map<String,Service> services = new HashMap<>();

    public  CustomerAccount(){
        services = new HashMap<String, Service>();
    }

    public void addService(String serviceName, Service service){
        this.services.put(serviceName, service);
    }

    public Service getService (String serviceName){
        return services.get(serviceName);
    }

    public void deleteService(String serviceName){
        this.services.remove(serviceName);
    }

    @Override
    public String getObjectId() {
        return objectId;
    }

    @Override
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
