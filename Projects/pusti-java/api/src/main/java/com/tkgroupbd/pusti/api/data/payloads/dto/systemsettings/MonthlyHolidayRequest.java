package com.tkgroupbd.pusti.api.data.payloads.dto.systemsettings;

import java.sql.Date;
import java.util.List;
import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MonthlyHolidayRequest extends BaseEntity {
    private String yearMonth;
    private int year;
    private int month;
    private List<Date> monthlyHolidayList;
}
