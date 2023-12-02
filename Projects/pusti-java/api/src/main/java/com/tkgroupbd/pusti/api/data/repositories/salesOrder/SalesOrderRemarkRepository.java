package com.tkgroupbd.pusti.api.data.repositories.salesOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tkgroupbd.pusti.api.data.models.entity.salesorders.SalesOrderRemark;

@Repository
public interface SalesOrderRemarkRepository extends JpaRepository<SalesOrderRemark, Integer> {
}
