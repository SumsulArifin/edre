package com.tkgroupbd.pusti.api.utils;

import java.time.YearMonth;

public class Utilities {
    public static int getNumberOfDaysInMonth(int year, int month) {
        YearMonth yearMonthObject = YearMonth.of(year, month);
        return yearMonthObject.lengthOfMonth();
    }
}
