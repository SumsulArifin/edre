package com.tkgroupbd.pusti.api.data.models.entity.orders.primary.approval;

import com.tkgroupbd.pusti.api.data.models.common.BaseEntity;
import com.tkgroupbd.pusti.api.data.models.entity.hrm.users.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Table(name = "primaryOrderApprovalActivities")
public class PrimaryOrderApprovalActivities extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int activityId;
    private String actionComments;

    @ManyToOne
    @JoinColumn(name = "primary_order_id")
    private PrimaryOrder primaryOrders;

    @ManyToOne
    @JoinColumn(name = "approverId")
    private User user;

}
