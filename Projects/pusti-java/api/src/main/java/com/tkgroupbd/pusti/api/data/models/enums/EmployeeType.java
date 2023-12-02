package com.tkgroupbd.pusti.api.data.models.enums;

public enum EmployeeType {
    DivisionalHead(1),
    ZonalHead(2),
    RegionalHead(3),
    AreaHead(4),
    SalesOfficer(5),
    DistributorSalesRepresentative(6),
    GeneralDistributor(7),
    SuperDistributor(8);

    private int code;

    private EmployeeType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
