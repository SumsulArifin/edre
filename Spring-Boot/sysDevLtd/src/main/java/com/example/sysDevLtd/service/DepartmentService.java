package com.example.sysDevLtd.service;
import com.example.sysDevLtd.entity.dto.DepartmentDTO;
import com.example.sysDevLtd.entity.model.Department;
import com.example.sysDevLtd.utils.MessageResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public interface DepartmentService {

    public MessageResponse saveDepartment(DepartmentDTO departmentDTO);
    public List<Department> getAllDepartment();
    public Department findDepartmentById(int deptId);
    public Optional<Department> updateDepartment(int deptId, DepartmentDTO departmentDTO);
    public void deleteDepartmentById(int deptId);



}
