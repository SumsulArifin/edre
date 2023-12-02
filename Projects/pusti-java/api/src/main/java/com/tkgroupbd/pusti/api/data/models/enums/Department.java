package com.tkgroupbd.pusti.api.data.models.enums;


public enum Department {
    HUMAN_RESOURCE(1),
    MARKETING(2),
    ENGINEERING(3);
    private int code;

    private Department (int code){
        this.code=code;
    }
    public int getCode(){
        return code;
    }
}