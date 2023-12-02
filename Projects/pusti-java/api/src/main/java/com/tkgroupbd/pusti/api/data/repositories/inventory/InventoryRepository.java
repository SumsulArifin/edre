package com.tkgroupbd.pusti.api.data.repositories.inventory;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tkgroupbd.pusti.api.data.models.entity.factory.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

}
