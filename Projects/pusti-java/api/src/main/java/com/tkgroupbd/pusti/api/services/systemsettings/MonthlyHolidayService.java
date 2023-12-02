package com.tkgroupbd.pusti.api.services.systemsettings;

import java.util.List;

import com.tkgroupbd.pusti.api.data.models.entity.systemsettings.MonthlyHoliday;
import com.tkgroupbd.pusti.api.data.payloads.dto.systemsettings.MonthlyHolidayRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;

public interface MonthlyHolidayService {

    MessageResponse saveMonthlyHolidays(MonthlyHolidayRequest request);

    MessageResponse updateMonthlyHolidays(MonthlyHolidayRequest request);

    List<MonthlyHoliday> getMonthlyHolidaysByMonthYear(Integer month, Integer year);
}
