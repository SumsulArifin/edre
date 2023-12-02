package com.tkgroupbd.pusti.api.services.orders.secondary;
// package com.tkgroupbd.pusti.api.services.secondaryorders;

// import
// com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.OutletAmendment;
// import
// com.tkgroupbd.pusti.api.data.repositories.mastersettings.sales.AssignedSalesOfficerRepository;
// import
// com.tkgroupbd.pusti.api.data.repositories.mastersettings.sales.OutletAmendmentRepository;
// import
// com.tkgroupbd.pusti.api.data.repositories.primaryorders.DailyOrdersSummaryRepository;
// import com.tkgroupbd.pusti.api.utils.constant.Message;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.time.LocalDate;
// import java.util.List;

// @Service
// public class SummarizedSecondarySalesServiceImpl implements
// SummarizedSecondarySalesService {

// @Autowired
// @SuppressWarnings(Message.AUTOWIRED_OK)
// private OutletAmendmentRepository outletAmendmentRepository;

// @Autowired
// @SuppressWarnings(Message.AUTOWIRED_OK)
// private AssignedSalesOfficerRepository assignedSalesOfficerRepository;

// @Autowired
// @SuppressWarnings(Message.AUTOWIRED_OK)
// private DailyOrdersSummaryRepository dailyOrdersSummaryRepository;

// public List<OutletAmendment> findByRoute(int id) {
// List<OutletAmendment> outletAmendments =
// outletAmendmentRepository.findByRoute(id);
// return outletAmendments;
// }

// /**
// * @return total visited outlet number by current date
// */
// public int findByTotalVisitedOutlet() {
// LocalDate today = LocalDate.now();
// int totalVisitedOutlet =
// assignedSalesOfficerRepository.findByTotalVisitedOutlet(today);
// return totalVisitedOutlet;
// }

// public int findByTotalScheduleOutlet() {
// LocalDate today = LocalDate.now();
// int totalScheduleOutlet =
// assignedSalesOfficerRepository.findByTotalScheduleOutlet(today);
// return totalScheduleOutlet;
// }

// /**
// * @return total unvisited outlet number by current date
// */

// public int totalUnVisitedOutlet() {
// int subUnVisitedOutlet = findByTotalScheduleOutlet() -
// findByTotalVisitedOutlet();
// return subUnVisitedOutlet;
// }

// /**
// * @return total Number of Memo by current day
// */

// public int totalNumMemo() {
// LocalDate today = LocalDate.now();
// int totalMemo = dailyOrdersSummaryRepository.findTotalMemo(today);
// return totalMemo;
// }

// /**
// * @return total categories by current date
// */

// public int totalCategories() {
// LocalDate today = LocalDate.now();
// int totalCategories =
// dailyOrdersSummaryRepository.findTotalCategories(today);
// return totalCategories;
// }

// /**
// * present of distributor or absent of distributor
// * @param status
// * @return
// */
// public int numberOfAbsentDistributor(boolean status) {
// LocalDate today = LocalDate.now();
// int numOfPreDistributor =
// dailyOrdersSummaryRepository.findTotalDistributor(today, status);
// return numOfPreDistributor;
// }

// }
