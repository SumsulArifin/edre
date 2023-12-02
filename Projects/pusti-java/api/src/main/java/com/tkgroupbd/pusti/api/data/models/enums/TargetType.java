package com.tkgroupbd.pusti.api.data.models.enums;

public enum TargetType {

    primary(1),
    secondary(2),
;

    private int code;

    private TargetType (int code){
        this.code=code;
    }
    public int getCode(){
        return code;
    }
}
