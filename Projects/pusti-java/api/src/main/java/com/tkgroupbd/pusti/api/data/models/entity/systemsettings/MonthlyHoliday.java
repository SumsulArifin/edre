package com.tkgroupbd.pusti.api.data.models.entity.systemsettings;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "monthly_holiday_settings")
public class MonthlyHoliday extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int holidayId;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date holidayDate;
    private Integer numberOfDaysInMonth;

    @JsonFormat(pattern = "yyyy-MM")
    private String yearMonth;
}
