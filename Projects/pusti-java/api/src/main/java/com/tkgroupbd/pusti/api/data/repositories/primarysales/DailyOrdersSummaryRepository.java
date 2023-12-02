// package com.tkgroupbd.pusti.api.data.repositories.primaryorders;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
// import org.springframework.stereotype.Repository;

// import
// com.tkgroupbd.pusti.api.data.models.entity.primaryorders.DailyOrdersSummary;

// import java.time.LocalDate;
// import java.util.List;

// @Repository
// public interface DailyOrdersSummaryRepository extends
// JpaRepository<DailyOrdersSummary, Integer> {
// // @Query("select d from DailyOrdersSummary d where
// // d.salesOfficer.assignedSoId=?1")
// public List<DailyOrdersSummary> findDailyOrdersSummaryBySoId(int id);

// @Query("select count (d.totalMemo) from DailyOrdersSummary d where
// d.createdAt=:date")
// int findTotalMemo(@Param("date") LocalDate date);

// @Query("select count (d.numberOfCategories) from DailyOrdersSummary d where
// d.createdAt=:date")
// int findTotalCategories(@Param("date") LocalDate date);

// @Query("select count (d.numberOfCategories) from DailyOrdersSummary d where
// d.createdAt=:date and d.status=:status")
// int findTotalDistributor(@Param("date") LocalDate date, @Param("status")
// boolean status);

// }
