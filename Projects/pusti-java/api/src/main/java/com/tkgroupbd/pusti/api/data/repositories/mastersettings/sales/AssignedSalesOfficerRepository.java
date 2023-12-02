package com.tkgroupbd.pusti.api.data.repositories.mastersettings.sales;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.AssignedSalesOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AssignedSalesOfficerRepository
      extends JpaRepository<AssignedSalesOfficer, Integer>, JpaSpecificationExecutor<AssignedSalesOfficer> {
   @Query("select count (a.visitedOutlet) from AssignedSalesOfficer a where a.createdAt=:date")
   public int findByTotalVisitedOutlet(@Param("date") LocalDate date);

   @Query("select count (a.scheduleOutlet) from AssignedSalesOfficer a where a.createdAt=:date")
   public int findByTotalScheduleOutlet(@Param("date") LocalDate date);

}
