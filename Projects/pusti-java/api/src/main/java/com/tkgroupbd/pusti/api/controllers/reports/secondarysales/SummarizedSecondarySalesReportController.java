// package com.tkgroupbd.pusti.api.controllers.reports.secondarysales;

// import
// com.tkgroupbd.pusti.api.data.models.entity.mastersettings.sales.OutletAmendment;
// import
// com.tkgroupbd.pusti.api.services.secondaryorders.SummarizedSecondarySalesServiceImpl;
// import com.tkgroupbd.pusti.api.utils.constant.Message;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @Tag(name = "Summarized Secondary Sales Report")
// @RestController
// @RequestMapping("/SummarizedSecondarySalesReport")
// public class SummarizedSecondarySalesReportController {

// @Autowired
// @SuppressWarnings(Message.AUTOWIRED_OK)
// private SummarizedSecondarySalesServiceImpl summarizedSecondarySalesService;

// @GetMapping("/getAllOutlet/by/route/{id}")
// public List<OutletAmendment> findOutletByRoute(@PathVariable("id") int id) {
// return summarizedSecondarySalesService.findByRoute(id);
// }

// @GetMapping("/find/total/unVisited/outlet")
// public int totalUnVisitedOutlet() {
// return summarizedSecondarySalesService.totalUnVisitedOutlet();
// }

// @GetMapping("/find/total/visited/outlet")
// public int totalVisitedOutlet() {
// return summarizedSecondarySalesService.findByTotalVisitedOutlet();
// }

// /**
// * @return total Number of Memo by current day
// */
// @GetMapping("/find/total/visited/memo")
// public int findTotalMemo() {
// return summarizedSecondarySalesService.totalNumMemo();
// }

// /**
// *
// * @return total categories by current date
// */
// @GetMapping("/find/total/visited/categories")
// public int totalCategories() {
// return summarizedSecondarySalesService.totalCategories();
// }

// /**
// * it will give whom order status is true or false
// * if true they are present
// * if false they are absent
// * for search need to give a param
// *
// * @param status
// * @return
// */
// @GetMapping("/find/total/distributor")
// public int totalPresentOrUnPresentOfDistributor(@RequestParam boolean status)
// {
// return summarizedSecondarySalesService.numberOfAbsentDistributor(status);
// }

// }
