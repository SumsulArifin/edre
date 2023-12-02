package com.tkgroupbd.pusti.api.services.hrm.employees;

import com.tkgroupbd.pusti.api.data.models.entity.hrm.employees.Employee;
import com.tkgroupbd.pusti.api.data.models.entity.hrm.employees.EmployeeEducation;
import com.tkgroupbd.pusti.api.data.models.enums.EmployeeType;
import com.tkgroupbd.pusti.api.data.payloads.dto.hrm.employees.EmployeeDTO;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface EmployeeService {
    MessageResponse saveEmployee(Employee employee);

    public MessageResponse updateEmployee(int id, Employee employee);

    public void deleteEmployeeById(int id);

    public Optional<Employee> updateEmployeeStatus(int id, Employee employee);

    List<Employee> findSortedEmployeeByKey(String field);

    public List<Employee> getAllEmployee();

    public EmployeeDTO findEmployeeById(int id);

    public List<EmployeeEducation> findEmployeeEducationByEmployeeId(int employeeId);

    public List<Employee> findEmployeeByType(EmployeeType employeeType);

    public Employee getEmployeeDetailsById(int employeeId);
}
