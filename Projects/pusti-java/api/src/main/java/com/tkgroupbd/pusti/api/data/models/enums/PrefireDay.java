package com.tkgroupbd.pusti.api.data.models.enums;

public enum PrefireDay {
    SATURDAY(1),
    SUNDAY(2),
    MONDAY(3),
    TUESDAY(4),
    WEDNESDAY(5),
    THURSDAY(6),
    FRIDAY(7),

    ;

    private final int code;

    PrefireDay(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
    public static PrefireDay fromValue(int value) {
        for (PrefireDay sourceType : PrefireDay.values()) {
            if (sourceType.code == value) {
                return sourceType;
            }
        }
        throw new IllegalArgumentException("Invalid PrefireDay value: " + value);
    }
}
