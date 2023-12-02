package com.tkgroupbd.pusti.api.data.models.enums;

public enum OrderType {
    primary(1),
    secondary(2),
    ;

    private int code;

    private OrderType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
