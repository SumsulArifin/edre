package com.tkgroupbd.pusti.api.data.models.enums;

public enum ApprovalType {
    PRIMARY_APPROVAL(1),
    DELIVERY_ORDER_APPROVAL(2);

    private int code;

    private ApprovalType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
