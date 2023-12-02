// package com.tkgroupbd.pusti.api.controllers.reports;

// import io.swagger.v3.oas.annotations.tags.Tag;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.tkgroupbd.pusti.api.services.salesorders.SalesOrderDetailsService;

// import java.util.List;

// @RestController
// @Tag(name = "Route Wise Sales")
// @RequestMapping("/routeWiseSKUSalesRepost")
// public class RouteWiseSKUSalesRepost {

// @Autowired
// SalesOrderDetailsService salesOrderDetailsService;

// @GetMapping("/getBySKU/{routeId}")
// public ResponseEntity<List<Object[]>>
// getSKUByRouteId(@PathVariable("routeId") Integer routeId) {
// List<Object[]> salesOrderDetails =
// salesOrderDetailsService.findSKUByRoute_ID(routeId);
// return new ResponseEntity<>(salesOrderDetails, HttpStatus.OK);
// }

// }
