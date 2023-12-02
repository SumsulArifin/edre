package com.tkgroupbd.pusti.api.controllers.systemsettings;

import com.tkgroupbd.pusti.api.data.models.entity.systemsettings.MonthlyHoliday;
import com.tkgroupbd.pusti.api.data.payloads.dto.systemsettings.MonthlyHolidayRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.systemsettings.MonthlyHolidayService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Monthly Holiday Settings")
@RestController
@RequestMapping("/")
public class MonthlyHolidayController {

    @Autowired
    private MonthlyHolidayService holidayService;

    @PostMapping("/saveMonthHolidays")
    public ResponseEntity<MessageResponse> createTimePass(
            @RequestBody MonthlyHolidayRequest request) {
        MessageResponse newTimePass = holidayService.saveMonthlyHolidays(request);
        return new ResponseEntity<>(newTimePass, HttpStatus.CREATED);
    }

    @GetMapping("/getHolidayRecordsByYearMonth/{year}/{month}")
    public ResponseEntity<List<MonthlyHoliday>> getHolidayRecordsByYearMonth(@PathVariable("year") Integer year,
            @PathVariable("month") Integer month) {
        List<MonthlyHoliday> holidays = holidayService.getMonthlyHolidaysByMonthYear(month, year);
        return new ResponseEntity<>(holidays, HttpStatus.OK);
    }

    @PutMapping("/updateMonthlyHolidays")
    public ResponseEntity<MessageResponse> updateMonthlyHolidays(@RequestBody MonthlyHolidayRequest request) {
        MessageResponse messageResponse = holidayService.updateMonthlyHolidays(request);
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }
}
