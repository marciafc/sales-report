package br.com.marcia.salesreport.eumeration;

public enum RegisterTypeEnum {

    SALESMAN("001"), CUSTOMER("002"), SALE("003");

    String value;

    RegisterTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
