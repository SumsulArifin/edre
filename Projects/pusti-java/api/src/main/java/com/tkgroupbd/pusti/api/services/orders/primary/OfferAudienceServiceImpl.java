package com.tkgroupbd.pusti.api.services.orders.primary;
// package com.tkgroupbd.pusti.api.services.primaryorders;

// import
// com.tkgroupbd.pusti.api.data.models.entity.primaryorders.OfferAudience;
// import
// com.tkgroupbd.pusti.api.data.payloads.requests.primaryorders.OfferAudienceRequest;
// import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
// import
// com.tkgroupbd.pusti.api.data.repositories.primaryorders.OfferAudienceRepository;
// import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
// import com.tkgroupbd.pusti.api.utils.constant.Message;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class OfferAudienceServiceImpl implements OfferAudienceService {

// @Autowired
// private OfferAudienceRepository repository;

// @Override
// public MessageResponse createOfferAudience(OfferAudienceRequest request) {

// try {
// OfferAudience offerAudience = new OfferAudience();

// offerAudience.setAudienceType(request.getAudienceType());
// offerAudience.setAudienceIds(request.getAudienceIds());
// offerAudience.setCreatedBy(request.getCreatedBy());
// offerAudience.setUpdatedBy(request.getUpdatedBy());
// offerAudience.setCreatedAt(request.getCreatedAt());
// offerAudience.setUpdatedAt(request.getUpdatedAt());
// offerAudience.setDeletedAt(request.getDeletedAt());
// offerAudience.setStatus(request.isStatus());
// offerAudience.setOffer(request.getOffer());

// repository.save(offerAudience);
// return new MessageResponse(Message.SUCCESS_CREATION);
// }catch (Exception e){
// return new MessageResponse(Message.FAILED_CREATION);
// }

// }

// @Override
// public Optional<OfferAudience> updateOfferAudience(Integer id,
// OfferAudienceRequest request) {
// Optional<OfferAudience> results = repository.findById(id);
// if (results.isPresent()){
// OfferAudience offerAudience = results.get();

// offerAudience.setAudienceType(request.getAudienceType());
// offerAudience.setAudienceIds(request.getAudienceIds());
// offerAudience.setCreatedBy(request.getCreatedBy());
// offerAudience.setUpdatedBy(request.getUpdatedBy());
// offerAudience.setCreatedAt(request.getCreatedAt());
// offerAudience.setUpdatedAt(request.getUpdatedAt());
// offerAudience.setDeletedAt(request.getDeletedAt());
// offerAudience.setStatus(request.isStatus());
// offerAudience.setOffer(request.getOffer());

// repository.save(offerAudience);

// }else{
// throw new ResourceNotFoundException("OfferAudience", "id", id);
// }

// return results;

// }

// @Override
// public List<OfferAudience> getOfferAudienceByOfferId(Integer id) {
// return repository.findOfferAudienceByOfferId(id);
// }

// @Override
// public List<OfferAudience> getAllOfferAudience() {
// return repository.findAll();
// }

// @Override
// public Optional<OfferAudience> offerAudienceStatusChange(Integer id,
// OfferAudienceRequest request) {
// Optional<OfferAudience> results = repository.findById(id);
// if (results.isPresent()){
// OfferAudience offerAudience = results.get();
// offerAudience.setStatus(request.isStatus());
// repository.save(offerAudience);

// }else{
// throw new ResourceNotFoundException("OfferAudience", "id", id);
// }

// return results;
// }
// }
