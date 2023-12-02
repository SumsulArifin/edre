package com.tkgroupbd.pusti.api.data.models.enums;

public enum StorageUnit {
    PIECE(1),
    PACKET(2),
    BAG(3);

    private int code;

    private StorageUnit(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
