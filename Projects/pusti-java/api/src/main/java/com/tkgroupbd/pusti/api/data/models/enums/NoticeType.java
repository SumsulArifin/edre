package com.tkgroupbd.pusti.api.data.models.enums;


public enum NoticeType {
    Text(1),
    JPG(2),
    PDF(3);


    private int code;

    private NoticeType (int code){
        this.code=code;
    }
    public int getCode(){
        return code;
    }
}
