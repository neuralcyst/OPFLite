package ru.ncedu.opflite.domain.model.account;

public enum ServiceStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private  String status;

    ServiceStatus(String status) {
        this.status = status;
    }

}
