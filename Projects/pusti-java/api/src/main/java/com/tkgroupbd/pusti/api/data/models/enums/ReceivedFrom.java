package com.tkgroupbd.pusti.api.data.models.enums;

public enum ReceivedFrom {
    DEPOT(1),
    SDB(2),
    FACTORY(3),
    OTHERS(4);

    private final int code;

    ReceivedFrom(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
    public static ReceivedFrom fromValue(int value) {
        for (ReceivedFrom sourceType : ReceivedFrom.values()) {
            if (sourceType.code == value) {
                return sourceType;
            }
        }
        throw new IllegalArgumentException("Invalid ReceivedFrom value: " + value);
    }

}
