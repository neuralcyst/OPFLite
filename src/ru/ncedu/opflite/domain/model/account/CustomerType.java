package ru.ncedu.opflite.domain.model.account;

public enum CustomerType {
    RESIDENTIAL("R"),
    ORGANIZATION("O");

    private final String contractId;

    CustomerType(String contractId) {
        this.contractId = contractId;
    }

    public static CustomerType getCustomerTypeByContractId(String contractId) {
        for (CustomerType customerType : values()) {
            if (customerType.contractId.equalsIgnoreCase(contractId)) {
                return customerType;
            }
        }
        return null;
    }

    public String getContractId() {
        return contractId;
    }
}
