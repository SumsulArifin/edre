package com.tkgroupbd.pusti.api.data.repositories.mastersettings.products.dbproducts;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.products.dbproducts.DbProductReceive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DbProductReceiveRepository extends JpaRepository<DbProductReceive, Integer> {
}
