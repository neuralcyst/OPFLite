package ru.ncedu.opflite.domain.model.account;



public class InternetService implements Service {

    private String internetSpeed;

    private String modemSerialNumber;

    private ServiceStatus status;

    public InternetService(){
        status = ServiceStatus.INACTIVE;
    }


    public String getInternetSpeed() {
        return internetSpeed;
    }

    public void setInternetSpeed(String internetSpeed) {
        this.internetSpeed = internetSpeed;
    }

    public String getModemSerialNumber() {
        return modemSerialNumber;
    }

    public void setModemSerialNumber(String modemSerialNumber) {
        this.modemSerialNumber = modemSerialNumber;
    }


    @Override
    public ServiceStatus getServiceStatus() {
        return status;
    }

    @Override
    public void setServiceStatus(ServiceStatus status) {
        this.status = status;
    }


}
