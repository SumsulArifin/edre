package com.tkgroupbd.pusti.api.data.models.enums;

public enum PaymentMode {
    Cash(1),
    Cheque(2),
    DD (3);

    private int code;

    private PaymentMode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
