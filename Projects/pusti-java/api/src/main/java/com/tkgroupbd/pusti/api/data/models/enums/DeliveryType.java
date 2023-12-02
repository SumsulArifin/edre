package com.tkgroupbd.pusti.api.data.models.enums;

public enum DeliveryType {
    PARTIAL(1),
    FULL(2),
    REMAINING(3);

    private final int code;

    DeliveryType(int code) {
        this.code = code;
    }
}
