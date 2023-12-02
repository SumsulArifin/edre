package com.tkgroupbd.pusti.api.data.repositories.orders.primary.approvals;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.hrm.users.User;
import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.approval.PrimaryOrderApprovalActivities;
import com.tkgroupbd.pusti.api.data.models.entity.orders.primary.approval.PrimaryOrderPaymentDetails;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrimaryOrderApprovalActivitiesRepository
        extends JpaRepository<PrimaryOrderApprovalActivities, Integer> {

}
