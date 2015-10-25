package ru.ncedu.opflite.domain.model.account;



public class TVService implements Service {

    private String channelPackage;

    private String cableTerminalSerialNumber;

    private ServiceStatus status;



    public TVService(){
        status = ServiceStatus.INACTIVE;
    }

    public String getChannelPackage() {
        return channelPackage;
    }

    public void setChannelPackage(String channelPackage) {
        this.channelPackage = channelPackage;
    }

    public String getCableTerminalSerialNumber() {
        return cableTerminalSerialNumber;
    }

    public void setCableTerminalSerialNumber(String cableTerminalSerialNumber) {
        this.cableTerminalSerialNumber = cableTerminalSerialNumber;
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
