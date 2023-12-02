package com.tkgroupbd.pusti.api.services.systemsettings;

import com.tkgroupbd.pusti.api.data.models.entity.systemsettings.MonthlyHoliday;
import com.tkgroupbd.pusti.api.data.payloads.dto.systemsettings.MonthlyHolidayRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.systemsettings.MonthHolidayRepository;
import com.tkgroupbd.pusti.api.utils.Utilities;
import com.tkgroupbd.pusti.api.utils.constant.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class MonthlyHolidayServiceImpl implements MonthlyHolidayService {

    @Autowired
    private MonthHolidayRepository holidayRepository;

    @Override
    public MessageResponse saveMonthlyHolidays(MonthlyHolidayRequest monthlyHolidayRequest) {
        List<MonthlyHoliday> monthlyHolidayList = new ArrayList<>();
        MonthlyHoliday holiday = null;

        for (Date monthlyHoliday : monthlyHolidayRequest.getMonthlyHolidayList()) {
            holiday = new MonthlyHoliday();
            holiday.setYearMonth(monthlyHolidayRequest.getYearMonth());
            holiday.setHolidayDate(monthlyHoliday);
            holiday.setStatus(monthlyHolidayRequest.isStatus());
            holiday.setCreatedAt(monthlyHolidayRequest.getCreatedAt());
            holiday.setUpdatedAt(monthlyHolidayRequest.getUpdatedAt());
            holiday.setDeletedAt(monthlyHolidayRequest.getDeletedAt());
            holiday.setBrowser(monthlyHolidayRequest.getBrowser());
            holiday.setIp(monthlyHolidayRequest.getIp());

            holiday.setNumberOfDaysInMonth(Utilities.getNumberOfDaysInMonth(monthlyHolidayRequest.getYear(),
                    monthlyHolidayRequest.getMonth()));
            monthlyHolidayList.add(holiday);
        }

        holidayRepository.saveAll(monthlyHolidayList);

        return new MessageResponse(Message.SUCCESS_CREATION);
    }

    @Override
    public MessageResponse updateMonthlyHolidays(MonthlyHolidayRequest monthlyHolidayRequest) {

        List<MonthlyHoliday> monthlyHolidayList = new ArrayList<>();
        MonthlyHoliday holiday = null;
        List<MonthlyHoliday> monthlyHolidays = holidayRepository
                .getListofHolidaysByYearMonth(monthlyHolidayRequest.getYearMonth());

        if (monthlyHolidays.size() > 0) {
            for (Date monthlyHoliday : monthlyHolidayRequest.getMonthlyHolidayList()) {
                holiday = new MonthlyHoliday();
                holiday.setYearMonth(monthlyHolidayRequest.getYearMonth());
                holiday.setHolidayDate(monthlyHoliday);
                holiday.setStatus(monthlyHolidayRequest.isStatus());
                holiday.setCreatedAt(monthlyHolidayRequest.getCreatedAt());
                holiday.setUpdatedAt(monthlyHolidayRequest.getUpdatedAt());
                holiday.setDeletedAt(monthlyHolidayRequest.getDeletedAt());
                holiday.setBrowser(monthlyHolidayRequest.getBrowser());
                holiday.setIp(monthlyHolidayRequest.getIp());

                holiday.setNumberOfDaysInMonth(Utilities.getNumberOfDaysInMonth(monthlyHolidayRequest.getYear(),
                        monthlyHolidayRequest.getMonth()));
                monthlyHolidayList.add(holiday);
            }

            holidayRepository.deleteByYearMonth(monthlyHolidayRequest.getYearMonth());

            holidayRepository.saveAll(monthlyHolidayList);
        }
        return new MessageResponse(Message.SUCCESS_CREATION);
    }

    @Override
    public List<MonthlyHoliday> getMonthlyHolidaysByMonthYear(Integer month, Integer year) {

        String yearMonth = year + "-" + month;
        return holidayRepository.getListofHolidaysByYearMonth(yearMonth);
    }
}
