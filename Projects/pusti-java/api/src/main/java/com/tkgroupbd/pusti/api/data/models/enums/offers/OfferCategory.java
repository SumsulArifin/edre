package com.tkgroupbd.pusti.api.data.models.enums.offers;

public enum OfferCategory {
    FREE_OFFER(1),
    TRADE_DISCOUNT(2);

    private int categoryCode;

    private OfferCategory(int code) {
        this.categoryCode = code;
    }

    public int getCode() {
        return categoryCode;
    }
}
