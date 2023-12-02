package com.tkgroupbd.pusti.api.data.models.enums;

public enum GroupId {
    DASHBOARD(1),
    MASTERSETTING(2),
    SALESORGANOGRAM(3),
    PRODUCTSETUP(4),
    TARGET(5),
    ORDER(7),
    PRODUCTCOLLECTION(6),
    COLLECTION(8);


    private int code;


    private GroupId(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
    }
