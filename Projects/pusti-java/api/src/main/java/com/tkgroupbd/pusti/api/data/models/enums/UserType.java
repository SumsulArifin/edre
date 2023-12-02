package com.tkgroupbd.pusti.api.data.models.enums;

public enum UserType {
    SUPER_ADMIN(1),
    ADMIN(2),
    SALES_OFFICER(3),
    DEALER(4),
    SUPER_ACCT_OFFICER(5),
    ACCT_OFFICER(6);

    private int userTypeCode;

    UserType(int typeCode) {
        this.userTypeCode = typeCode;
    }

    public void getCode(int typeCode) {
        this.userTypeCode = typeCode;
    }
}