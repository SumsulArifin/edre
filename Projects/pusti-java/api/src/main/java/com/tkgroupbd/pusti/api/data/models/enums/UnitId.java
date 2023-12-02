package com.tkgroupbd.pusti.api.data.models.enums;

public enum UnitId {
    Box(1),
    Jar(2),
    Piece(3);

    private int code;

    private UnitId(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
