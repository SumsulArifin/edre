package com.tkgroupbd.pusti.api.services.orders.primary;
// package com.tkgroupbd.pusti.api.services.primaryorders;

// import
// com.tkgroupbd.pusti.api.data.models.entity.primaryorders.DailyOrdersSummary;
// import
// com.tkgroupbd.pusti.api.data.payloads.requests.primaryorders.DailyOrdersSummaryRequest;
// import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
// import
// com.tkgroupbd.pusti.api.data.repositories.primaryorders.DailyOrdersSummaryRepository;
// import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
// import com.tkgroupbd.pusti.api.utils.constant.Message;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class DailyOrdersSummaryServiceImpl implements
// DailyOrdersSummaryService {

// @Autowired
// DailyOrdersSummaryRepository dailyOrdersSummaryRepository;

// @Override
// public MessageResponse saveDailyOrdersSummary(DailyOrdersSummaryRequest
// dailyOrdersSummaryRequest) {
// DailyOrdersSummary dailyOrdersSummary = new DailyOrdersSummary();
// dailyOrdersSummary.setNumberOfSkus(dailyOrdersSummaryRequest.getNumberOfSkus());
// dailyOrdersSummary.setNumberOfCategories(dailyOrdersSummaryRequest.getNumberOfCategories());
// dailyOrdersSummary.setCcp(dailyOrdersSummaryRequest.getCcp());
// dailyOrdersSummary.setLpc(dailyOrdersSummaryRequest.getLpc());
// dailyOrdersSummary.setTotalMemo(dailyOrdersSummaryRequest.getTotalMemo());
// //
// dailyOrdersSummary.setSalesOfficer(dailyOrdersSummaryRequest.getSalesOfficer());
// dailyOrdersSummary.setCreatedBy(dailyOrdersSummaryRequest.getCreatedBy());
// dailyOrdersSummary.setUpdatedBy(dailyOrdersSummaryRequest.getUpdatedBy());
// dailyOrdersSummary.setCreatedAt(dailyOrdersSummaryRequest.getCreatedAt());
// dailyOrdersSummary.setUpdatedAt(dailyOrdersSummaryRequest.getUpdatedAt());
// dailyOrdersSummary.setDeletedAt(dailyOrdersSummaryRequest.getDeletedAt());
// dailyOrdersSummaryRepository.save(dailyOrdersSummary);
// return new MessageResponse(Message.SUCCESS_CREATION);
// }

// @Override
// public Optional<DailyOrdersSummary> updateDailyOrdersSummary(int id,
// DailyOrdersSummaryRequest dailyOrdersSummaryRequest) {
// Optional<DailyOrdersSummary> result =
// dailyOrdersSummaryRepository.findById(id);
// if (result.isPresent()) {
// DailyOrdersSummary dailyOrdersSummary = result.get();
// dailyOrdersSummary.setNumberOfSkus(dailyOrdersSummaryRequest.getNumberOfSkus());
// dailyOrdersSummary.setNumberOfCategories(dailyOrdersSummaryRequest.getNumberOfCategories());
// dailyOrdersSummary.setCcp(dailyOrdersSummaryRequest.getCcp());
// dailyOrdersSummary.setLpc(dailyOrdersSummaryRequest.getLpc());
// //
// dailyOrdersSummary.setSalesOfficer(dailyOrdersSummaryRequest.getSalesOfficer());
// dailyOrdersSummary.setCreatedBy(dailyOrdersSummaryRequest.getCreatedBy());
// dailyOrdersSummary.setUpdatedBy(dailyOrdersSummaryRequest.getUpdatedBy());
// dailyOrdersSummary.setCreatedAt(dailyOrdersSummaryRequest.getCreatedAt());
// dailyOrdersSummary.setUpdatedAt(dailyOrdersSummaryRequest.getUpdatedAt());
// dailyOrdersSummary.setDeletedAt(dailyOrdersSummaryRequest.getDeletedAt());
// dailyOrdersSummaryRepository.save(dailyOrdersSummary);
// } else {
// throw new ResourceNotFoundException("DailyOrdersSummary", "id", id);
// }

// return result;
// }

// @Override
// public List<DailyOrdersSummary> getAllDailyOrdersSummary() {
// return dailyOrdersSummaryRepository.findAll();
// }

// @Override
// public List<DailyOrdersSummary> getDailyOrdersSummaryBySoId(int id) {
// return dailyOrdersSummaryRepository.findDailyOrdersSummaryBySoId(id);
// }
// }
