// package com.tkgroupbd.pusti.api.services.inventory;

// import com.tkgroupbd.pusti.api.data.models.entity.inventory.ProductStockIn;
// import com.tkgroupbd.pusti.api.data.models.enums.ReceivedFrom;
// import
// com.tkgroupbd.pusti.api.data.payloads.requests.inventory.ProductStockInRequest;
// import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
// import
// com.tkgroupbd.pusti.api.data.repositories.inventory.ProductStockInRepository;
// import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
// import com.tkgroupbd.pusti.api.utils.constant.Message;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.time.LocalDate;
// import java.util.List;
// import java.util.Locale;
// import java.util.Optional;

// @Service
// public class ProductStockInServiceImpl implements ProductStockInService {

// @Autowired
// private ProductStockInRepository repository;

// @Override
// public MessageResponse createCollectProduct(ProductStockInRequest request) {
// ProductStockIn productStockIn = new ProductStockIn();

// productStockIn.setSkuName(request.getSkuName());
// productStockIn.setQuantity(request.getQuantity());
// productStockIn.setRemarks(request.getRemarks());
// productStockIn.setUnitId(request.getUnitId());
// productStockIn.setCreatedAt(request.getCreatedAt());
// productStockIn.setUpdatedAt(request.getUpdatedAt());
// productStockIn.setDeletedAt(request.getDeletedAt());
// productStockIn.setStatus(request.isStatus());
// productStockIn.setReceivedFrom(request.getReceivedFrom());
// productStockIn.setProducts(request.getProducts());
// productStockIn.setDistributor(request.getDistributor());
// productStockIn.setFactory(request.getFactory());

// repository.save(productStockIn);

// return new MessageResponse(Message.SUCCESS_CREATION);
// }

// @Override
// public Optional<ProductStockIn> updateProduct(int id, ProductStockInRequest
// request) {
// Optional<ProductStockIn> result = repository.findById(id);

// if (result.isPresent()) {
// ProductStockIn productStockIn = result.get();

// productStockIn.setSkuName(request.getSkuName());

// productStockIn.setRemarks(request.getRemarks());
// productStockIn.setUnitId(request.getUnitId());
// productStockIn.setCreatedAt(request.getCreatedAt());
// productStockIn.setUpdatedAt(request.getUpdatedAt());
// productStockIn.setDeletedAt(request.getDeletedAt());
// productStockIn.setStatus(request.isStatus());
// productStockIn.setReceivedFrom(request.getReceivedFrom());
// productStockIn.setProducts(request.getProducts());
// productStockIn.setDistributor(request.getDistributor());
// productStockIn.setFactory(request.getFactory());
// productStockIn.setQuantity(request.getQuantity());

// repository.save(productStockIn);

// } else {
// throw new ResourceNotFoundException("ProductStockIn", "id", id);
// }

// return result;
// }

// @Override
// public List<ProductStockIn> getAllByCreationDateDesc() {
// return repository.findAllByOrderByCreatedAtDesc();
// }

// @Override
// public List<ProductStockIn> getByCreatedAt(String createdAt) {
// return
// repository.findAllByCreatedAtContaining(createdAt.toUpperCase(Locale.ROOT));
// }

// @Override
// public List<ProductStockIn> findByReceivedFrom(ReceivedFrom receivedFrom) {
// return repository.findByReceivedFrom(receivedFrom);
// }

// @Override
// public List<Object[]> findSKUByDistributor_id(int distributor_id) {
// return repository.findSOCKByDistributorId(distributor_id);
// }

// @Override
// public List<Object[]> findSKUByDistributor_idWithDate(int distributor_id,
// LocalDate startDate, LocalDate endDate) {
// return repository.findSOCKByDistributorIdWithDate(distributor_id, startDate,
// endDate);
// }

// @Override
// public List<Object[]> findStockyDistributor_idWithDate(int distributor_id,
// LocalDate startDate, LocalDate endDate) {
// return repository.findStockByDistributorIdWithDate(distributor_id, startDate,
// endDate);
// }

// }
