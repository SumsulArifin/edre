package com.tkgroupbd.pusti.api.data.repositories.common;

import com.tkgroupbd.pusti.api.data.models.common.UsersMenuPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersMenuPermissionRepository extends JpaRepository<UsersMenuPermission,Integer> {
}
