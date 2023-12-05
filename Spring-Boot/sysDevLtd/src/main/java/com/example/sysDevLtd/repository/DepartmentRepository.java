package com.example.sysDevLtd.repository;

import com.example.sysDevLtd.entity.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department ,Integer> {
}
