package ru.ncedu.opflite.domain.model.account;


public interface Service {
    ServiceStatus getServiceStatus();
    void setServiceStatus(ServiceStatus status);

}
