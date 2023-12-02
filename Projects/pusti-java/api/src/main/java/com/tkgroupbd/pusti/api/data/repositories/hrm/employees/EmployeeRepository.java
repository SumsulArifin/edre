package com.tkgroupbd.pusti.api.data.repositories.hrm.employees;

import com.tkgroupbd.pusti.api.data.models.entity.hrm.employees.Employee;
import com.tkgroupbd.pusti.api.data.models.enums.EmployeeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
        List<Employee> findByEmployeeType(EmployeeType employeeType);

        @Query("SELECT emp FROM Employee emp LEFT JOIN emp.educationList where emp.Id=?1")
        Employee getEmployeeDetailsById(int employeeId);
}
