package com.tkgroupbd.pusti.api.data.repositories.hrm.employees;

import com.tkgroupbd.pusti.api.data.models.entity.hrm.employees.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tkgroupbd.pusti.api.data.models.entity.hrm.employees.EmployeeEducation;

import java.util.List;

@Repository
public interface EmployeeEducationRepository extends JpaRepository<EmployeeEducation, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM EmployeeEducation ee WHERE ee.employee.employeeId = :employeeId")
    void deleteByEmployeeId(@Param("employeeId") int employeeId);

    @Query("select e from EmployeeEducation  e where e.employee.employeeId=?1")
    List<EmployeeEducation> findEmployeeEducationByEmployeeId(int employeeId);

    List<EmployeeEducation> findByEmployee(Employee employee);
}
