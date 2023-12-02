package com.tkgroupbd.pusti.api.data.repositories.mastersettings.areas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.areas.Upazila;

import java.util.List;

@Repository
public interface UpazilaRepository extends JpaRepository<Upazila, Integer> {

    @Query("SELECT u FROM Upazila u WHERE u.upazilaName LIKE %:upazilaName% ESCAPE '!' ORDER BY u.upazilaName ASC")
    List<Upazila> findByUpazilaNameContaining(String upazilaName);
}
