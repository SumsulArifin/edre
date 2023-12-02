package com.tkgroupbd.pusti.api.controllers.hrm.employees;

import com.tkgroupbd.pusti.api.data.models.common.ApiResponse;
import com.tkgroupbd.pusti.api.data.models.entity.hrm.employees.Employee;
import com.tkgroupbd.pusti.api.data.models.entity.hrm.employees.EmployeeEducation;
import com.tkgroupbd.pusti.api.data.models.enums.EmployeeType;
import com.tkgroupbd.pusti.api.data.payloads.dto.hrm.employees.EmployeeDTO;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.hrm.employees.EmployeeEducationRepository;
import com.tkgroupbd.pusti.api.data.repositories.hrm.employees.EmployeeRepository;
import com.tkgroupbd.pusti.api.services.hrm.employees.EmployeeService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Tag(name = "Employee")
@RestController
@RequestMapping("/employee")
public class EmployeesController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeEducationRepository employeeEducationRepository;

    @PostMapping("/addNewEmployee")
    public ResponseEntity<MessageResponse> employeesSave(@RequestBody @Valid Employee employee) {
        MessageResponse newEmp = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(newEmp, HttpStatus.CREATED);
    }

    // retrieve all Employee with employee education it worked properly

    @GetMapping("/getAllEmployees")
    public ResponseEntity<List<Employee>> getAllEmployeesWithEducation() {
        List<Employee> employees = employeeRepository.findAll();
        for (Employee employee : employees) {
            List<EmployeeEducation> educationList = employeeEducationRepository.findByEmployee(employee);
            employee.setEducationList(educationList);
        }

        return ResponseEntity.ok(employees);
    }
    // @GetMapping("/getAllEmployees")
    // @ResponseBody
    // public ResponseEntity<List<Employee>> getAllEmployees() {
    // List<Employee> employees = employeeService.getAllEmployee();
    // return new ResponseEntity<>(employees, HttpStatus.OK);
    // }

    // Update a Employee information
    @PutMapping("/updateEmployee/{employeeId}")
    public ResponseEntity<MessageResponse> updateEmployees(@PathVariable Integer employeeId,
            @RequestBody Employee employeesRequest) {
        MessageResponse modifiedEmployee = employeeService.updateEmployee(employeeId, employeesRequest);
        return new ResponseEntity<>(modifiedEmployee, HttpStatus.OK);
    }

    // Employee Status Change API
    @PutMapping("/statusChange/{employeeId}")
    public ResponseEntity<Optional<Employee>> changeEmployeesStatus(@PathVariable Integer employeeId,
            @RequestBody Employee employeesRequest) {
        Optional<Employee> employees = employeeService.updateEmployeeStatus(employeeId, employeesRequest);
        employeeEducationRepository.deleteByEmployeeId(employeeId);
        return new ResponseEntity<Optional<Employee>>(employees, HttpStatus.OK);
    }

    @GetMapping("/getEmployeeDetailsById/{id}")
    public ResponseEntity<Employee> getEmployeeDetailsById(@PathVariable("id") Integer id) {
        Employee employeeDetails = employeeService.getEmployeeDetailsById(id);
        return new ResponseEntity<Employee>(employeeDetails, HttpStatus.OK);
    }

    // API to retrieve Employee by id
    @GetMapping("/getEmployeeById/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeesById(@PathVariable("employeeId") Integer employeeId) {
        EmployeeDTO employeeRequest = employeeService.findEmployeeById(employeeId);
        return new ResponseEntity<>(employeeRequest, HttpStatus.OK);
    }

    // API for search by keyword

    @GetMapping("/getSortedEmployeeByKey/{field}")
    private ApiResponse<List<Employee>> getSortedEmployeesByKey(@PathVariable String field) {
        List<Employee> employeesList = employeeService.findSortedEmployeeByKey(field);
        return new ApiResponse(employeesList.size(), employeesList);
    }

    // API for search by EmployeeType
    @GetMapping("getEmployeeByType/{employeeType}")
    public List<Employee> getEmpByEmpType(@PathVariable EmployeeType employeeType) {
        List<Employee> byEmployeeType = employeeService.findEmployeeByType(employeeType);
        return byEmployeeType;
    }

    @GetMapping("getEmployeeEducationBy/{employeeId}")
    public List<EmployeeEducation> getEmployeeEducationByEmpId(@PathVariable("employeeId") int employeeId) {
        List<EmployeeEducation> employeeEducations = employeeService.findEmployeeEducationByEmployeeId(employeeId);
        return employeeEducations;
    }

}
