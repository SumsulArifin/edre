// package com.tkgroupbd.pusti.api.services.salesorders;

// import
// com.tkgroupbd.pusti.api.data.models.entity.salesorders.SalesOrderOutletCoverage;
// import
// com.tkgroupbd.pusti.api.data.payloads.requests.salesOrder.SalesOrderOutletCoverageRequest;
// import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
// import
// com.tkgroupbd.pusti.api.data.repositories.salesOrder.SalesOrderOutletCoverageRepository;
// import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
// import com.tkgroupbd.pusti.api.utils.constant.Message;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class SalesOrderOutletCoverageServiceImpl implements
// SalesOrderOutletCoverageService {

// @Autowired
// SalesOrderOutletCoverageRepository salesOrderOutletCoverageRepository;

// @Override
// public MessageResponse createSalesOrderOutletCoverage(
// SalesOrderOutletCoverageRequest salesOrderOutletCoverageRequest) {
// try {
// SalesOrderOutletCoverage salesOrderOutletCoverage = new
// SalesOrderOutletCoverage();
// salesOrderOutletCoverage.setQuantity(salesOrderOutletCoverageRequest.getQuantity());
// salesOrderOutletCoverage.setAssignedSalesOfficer(salesOrderOutletCoverageRequest.getAssignedSalesOfficer());
// salesOrderOutletCoverage.setOutlet(salesOrderOutletCoverageRequest.getOutlet());
// salesOrderOutletCoverage.setBrand(salesOrderOutletCoverageRequest.getBrand());
// salesOrderOutletCoverage.setDistributor(salesOrderOutletCoverageRequest.getDistributor());
// salesOrderOutletCoverage.setRoute(salesOrderOutletCoverageRequest.getRoute());
// salesOrderOutletCoverage.setCreatedAt(salesOrderOutletCoverageRequest.getCreatedAt());
// salesOrderOutletCoverage.setCreatedBy(salesOrderOutletCoverageRequest.getCreatedBy());
// salesOrderOutletCoverage.setIp(salesOrderOutletCoverageRequest.getIp());
// salesOrderOutletCoverage.setBrowser(salesOrderOutletCoverageRequest.getBrowser());
// salesOrderOutletCoverageRepository.save(salesOrderOutletCoverage);
// return new MessageResponse(Message.SUCCESS_CREATION);

// } catch (Exception e) {
// return new MessageResponse(Message.FAILED_CREATION);
// }

// }

// @Override
// public Optional<SalesOrderOutletCoverage> updateSalesOrderOutletCoverage(int
// id,
// SalesOrderOutletCoverageRequest salesOrderOutletCoverageRequest) {
// Optional<SalesOrderOutletCoverage> result =
// salesOrderOutletCoverageRepository.findById(id);

// if (result.isPresent()) {

// SalesOrderOutletCoverage salesOrderOutletCoverage = result.get();
// salesOrderOutletCoverage.setQuantity(salesOrderOutletCoverageRequest.getQuantity());
// salesOrderOutletCoverage.setAssignedSalesOfficer(salesOrderOutletCoverageRequest.getAssignedSalesOfficer());
// salesOrderOutletCoverage.setOutlet(salesOrderOutletCoverageRequest.getOutlet());
// salesOrderOutletCoverage.setBrand(salesOrderOutletCoverageRequest.getBrand());
// salesOrderOutletCoverage.setDistributor(salesOrderOutletCoverageRequest.getDistributor());
// salesOrderOutletCoverage.setRoute(salesOrderOutletCoverageRequest.getRoute());
// salesOrderOutletCoverage.setCreatedAt(salesOrderOutletCoverageRequest.getCreatedAt());
// salesOrderOutletCoverage.setCreatedBy(salesOrderOutletCoverageRequest.getCreatedBy());
// salesOrderOutletCoverage.setIp(salesOrderOutletCoverageRequest.getIp());
// salesOrderOutletCoverage.setBrowser(salesOrderOutletCoverageRequest.getBrowser());
// salesOrderOutletCoverageRepository.save(salesOrderOutletCoverage);

// } else {

// throw new ResourceNotFoundException("SalesOrderOutletCoverage", "id", id);
// }

// return result;
// }

// @Override
// public SalesOrderOutletCoverage getSalesOrderOutletCoverageById(Integer id) {
// return salesOrderOutletCoverageRepository.findById(id).get();
// }

// @Override
// public List<SalesOrderOutletCoverage> getAllSalesOrderOutletCoverage() {
// return salesOrderOutletCoverageRepository.findAll();
// }

// @Override
// public List<SalesOrderOutletCoverage>
// getSalesOrderOutletCoverageByOutletId(int id) {
// return
// salesOrderOutletCoverageRepository.findSalesOrderOutletCoverageByOutletId(id);
// }
// }
