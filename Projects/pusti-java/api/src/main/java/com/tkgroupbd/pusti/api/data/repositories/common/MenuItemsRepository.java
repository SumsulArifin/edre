package com.tkgroupbd.pusti.api.data.repositories.common;
import com.tkgroupbd.pusti.api.data.models.common.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemsRepository extends JpaRepository<MenuItem,Integer> {
}
