package com.tkgroupbd.pusti.api.data.models.enums;

public enum PDSStatus {
    planned(1),
    delivered(2),
    pause(3);

    private int code;

    private PDSStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
