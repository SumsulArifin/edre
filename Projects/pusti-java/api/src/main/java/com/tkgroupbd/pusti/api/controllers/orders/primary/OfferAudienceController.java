package com.tkgroupbd.pusti.api.controllers.orders.primary;
// package com.tkgroupbd.pusti.api.controllers.primarysales.primaryorders;

// import
// com.tkgroupbd.pusti.api.data.models.entity.primaryorders.OfferAudience;
// import
// com.tkgroupbd.pusti.api.data.payloads.requests.primaryorders.OfferAudienceRequest;
// import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
// import
// com.tkgroupbd.pusti.api.services.primaryorders.OfferAudienceServiceImpl;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Optional;

// @Tag(name = "Offer Audience")
// @RestController
// @RequestMapping("/offerAudience")
// public class OfferAudienceController {

// @Autowired
// OfferAudienceServiceImpl service;

// @GetMapping("/all")
// public ResponseEntity<List<OfferAudience>> getAllOfferAudience() {
// List<OfferAudience> offerAudiences = service.getAllOfferAudience();
// return new ResponseEntity<>(offerAudiences, HttpStatus.OK);
// }

// @PostMapping("/add")
// public ResponseEntity<MessageResponse> addOfferAudience(@RequestBody
// OfferAudienceRequest request) {
// MessageResponse offerAudience = service.createOfferAudience(request);
// return new ResponseEntity<>(offerAudience, HttpStatus.CREATED);
// }

// @GetMapping("/getByOfferId/{id}")
// public ResponseEntity<List<OfferAudience>>
// getOfferAudienceByOfferId(@PathVariable("id") Integer id) {
// List<OfferAudience> offerAudience = service.getOfferAudienceByOfferId(id);
// return new ResponseEntity<>(offerAudience, HttpStatus.OK);
// }

// @PutMapping("/update/{id}")
// public ResponseEntity<Optional<OfferAudience>>
// updateOfferAudience(@PathVariable Integer id,
// @RequestBody OfferAudienceRequest request) {
// Optional<OfferAudience> offerAudience = service.updateOfferAudience(id,
// request);
// return new ResponseEntity<Optional<OfferAudience>>(offerAudience,
// HttpStatus.OK);
// }

// @PutMapping("/status/change/{id}")
// public ResponseEntity<Optional<OfferAudience>>
// statusChangeOfferAudience(@PathVariable Integer id,
// @RequestBody OfferAudienceRequest request) {
// Optional<OfferAudience> offerAudience = service.offerAudienceStatusChange(id,
// request);
// return new ResponseEntity<Optional<OfferAudience>>(offerAudience,
// HttpStatus.OK);
// }

// }
