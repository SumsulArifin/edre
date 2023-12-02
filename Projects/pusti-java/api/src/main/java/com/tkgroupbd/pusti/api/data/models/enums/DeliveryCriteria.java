package com.tkgroupbd.pusti.api.data.models.enums;

public enum DeliveryCriteria {
    PIECE(1),
    PACKAGE(2),
    CARTOON(3),
    BAG(4);

    private int code;

    private DeliveryCriteria(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
