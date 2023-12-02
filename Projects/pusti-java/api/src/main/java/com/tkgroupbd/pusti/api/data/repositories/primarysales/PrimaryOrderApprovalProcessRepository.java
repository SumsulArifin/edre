package com.tkgroupbd.pusti.api.data.repositories.primarysales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.PrimaryOrderApprovalProcess;

@Repository
public interface PrimaryOrderApprovalProcessRepository extends JpaRepository<PrimaryOrderApprovalProcess, Integer> {
}
