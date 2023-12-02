package com.tkgroupbd.pusti.api.data.models.enums;

public enum AudienceType {


    DIVISION(0),
    REGION(1),
    AREA(2),
    ROUTE(3),
    OUTLETS(4),
    DB(5),
    SO(6),
    DSR(7);

    private int code;

    private AudienceType (int code){
        this.code=code;
    }
    public int getCode(){
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
