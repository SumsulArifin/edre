package com.tkgroupbd.pusti.api.data.repositories.orders.primary.approvals;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.approval.PrimaryOrderProductDetails;

@Repository
public interface PrimaryOrderProductDetailsRepository extends JpaRepository<PrimaryOrderProductDetails, Integer> {

}
