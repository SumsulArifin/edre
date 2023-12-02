package com.tkgroupbd.pusti.api.data.repositories.depot;

import com.tkgroupbd.pusti.api.data.models.entity.depot.ReceivingEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceivingEntryRepository extends JpaRepository<ReceivingEntry,Integer> {
}
