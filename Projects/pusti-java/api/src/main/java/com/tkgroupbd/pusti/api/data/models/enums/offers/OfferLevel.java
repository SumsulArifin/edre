package com.tkgroupbd.pusti.api.data.models.enums.offers;

public enum OfferLevel {
    NATIONAL(1),
    DIVISION(2),
    REGION(3),
    AREA(4),
    ROUTE(5),
    OUTLET(6),
    DISTRIBUTOR(7),
    BRAND(8);

    private int offerLevelCode;

    private OfferLevel(int code) {
        this.offerLevelCode = code;
    }

    public int getCode() {
        return offerLevelCode;
    }
}
