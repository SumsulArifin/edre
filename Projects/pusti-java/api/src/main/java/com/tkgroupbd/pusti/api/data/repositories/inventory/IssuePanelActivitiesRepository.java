package com.tkgroupbd.pusti.api.data.repositories.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tkgroupbd.pusti.api.data.models.entity.factory.IssuePanelActivities;

@Repository
public interface IssuePanelActivitiesRepository extends JpaRepository<IssuePanelActivities, Integer> {
}
