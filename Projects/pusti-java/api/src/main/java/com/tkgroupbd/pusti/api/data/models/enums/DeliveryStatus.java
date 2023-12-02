package com.tkgroupbd.pusti.api.data.models.enums;

public enum DeliveryStatus {
    COMPLETED(1),
    IN_PROGRESS(2),
    PAUSED(3);

    private int code;

    DeliveryStatus(int code) {
        this.code = code;
    }

    public void getCode(int code) {
        this.code = code;
    }

}
