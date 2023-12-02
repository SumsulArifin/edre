package com.tkgroupbd.pusti.api.data.repositories.systemsettings;

import com.tkgroupbd.pusti.api.data.models.entity.systemsettings.MonthlyHoliday;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthHolidayRepository extends JpaRepository<MonthlyHoliday, Integer> {
    @Transactional
    @Query("SELECT holiday FROM MonthlyHoliday holiday WHERE holiday.yearMonth = :yearMonth")
    List<MonthlyHoliday> getListofHolidaysByYearMonth(@Param("yearMonth") String yearMonth);

    @Transactional
    @Modifying
    @Query("DELETE FROM MonthlyHoliday WHERE yearMonth = :yearMonth")
    void deleteByYearMonth(@Param("yearMonth") String yearMonth);
}
