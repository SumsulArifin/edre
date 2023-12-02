package com.tkgroupbd.pusti.api.services.hrm.employees;

import com.tkgroupbd.pusti.api.data.models.entity.hrm.employees.Employee;
import com.tkgroupbd.pusti.api.data.models.entity.hrm.employees.EmployeeEducation;
import com.tkgroupbd.pusti.api.data.models.enums.EmployeeType;
import com.tkgroupbd.pusti.api.data.payloads.dto.hrm.employees.EmployeeDTO;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.hrm.employees.EmployeeEducationRepository;
import com.tkgroupbd.pusti.api.data.repositories.hrm.employees.EmployeeRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeesServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeesRepository;

    @Autowired
    EmployeeEducationRepository employeeEducationRepository;

    @Override
    @Transactional(value = TxType.REQUIRED)
    public MessageResponse saveEmployee(Employee employee) {
        Employee objNewEmployee = new Employee();
        List<EmployeeEducation> employeeEducations = new ArrayList<>();
        objNewEmployee.setEmployeeId(employee.getEmployeeId());
        objNewEmployee.setEmployeeType(employee.getEmployeeType());
        objNewEmployee.setFirstName(employee.getFirstName());
        objNewEmployee.setMiddleInitial(employee.getMiddleInitial());
        objNewEmployee.setLastName(employee.getLastName());
        objNewEmployee.setDateOfBirth(employee.getDateOfBirth());
        objNewEmployee.setBloodGroup(employee.getBloodGroup());
        objNewEmployee.setNidNumber(employee.getNidNumber());
        objNewEmployee.setSalesOrganization(employee.getSalesOrganization());
        objNewEmployee.setMobileNumber(employee.getMobileNumber());
        objNewEmployee.setEmail(employee.getEmail());
        objNewEmployee.setAddress(employee.getAddress());
        objNewEmployee.setDivision(employee.getDivision());
        objNewEmployee.setRegion(employee.getRegion());
        objNewEmployee.setZone(employee.getZone());
        objNewEmployee.setDesignation(employee.getDesignation());
        objNewEmployee.setRoute(employee.getRoute());
        objNewEmployee.setPostalCode(employee.getPostalCode());
        objNewEmployee.setUpazilaId(employee.getUpazilaId());
        objNewEmployee.setDistrictId(employee.getDistrictId());
        objNewEmployee.setDateOfJoining(employee.getDateOfJoining());
        objNewEmployee.setDateOfResignation(employee.getDateOfResignation());
        objNewEmployee.setBasicSalary(employee.getBasicSalary());
        objNewEmployee.setHouseRent(employee.getHouseRent());
        objNewEmployee.setMedicalAllowance(employee.getMedicalAllowance());
        objNewEmployee.setInternetBill(employee.getInternetBill());
        objNewEmployee.setMobileBill(employee.getMobileBill());
        objNewEmployee.setTravellingDailyAllowance(employee.getTravellingDailyAllowance());
        objNewEmployee.setMeetingTravellingAllowance(employee.getMeetingTravellingAllowance());
        objNewEmployee.setMeetingDailyAllowance(employee.getMeetingDailyAllowance());
        objNewEmployee.setCityAllowance(employee.getCityAllowance());
        objNewEmployee.setOtherAllowance(employee.getOtherAllowance());
        objNewEmployee.setContributionPercentage(employee.getContributionPercentage());
        objNewEmployee.setEmergencyContactName(employee.getEmergencyContactName());
        objNewEmployee.setEmergencyMobileNumber(employee.getEmergencyMobileNumber());
        objNewEmployee.setEmergencyContactRelation(employee.getEmergencyContactRelation());
        objNewEmployee.setBank(employee.getBank());
        objNewEmployee.setBackAccNumber(employee.getBackAccNumber());
        objNewEmployee.setStatus(employee.isStatus());
        objNewEmployee.setCreatedAt(employee.getCreatedAt());
        objNewEmployee.setUpdatedAt(employee.getUpdatedAt());
        objNewEmployee.setDeletedAt(employee.getDeletedAt());
        objNewEmployee.setBrowser(employee.getBrowser());
        objNewEmployee.setIp(employee.getIp());
        var result = employeesRepository.save(objNewEmployee);

        for (EmployeeEducation employeeEducation : employee.getEducationList()) {
            EmployeeEducation objEmpEducation = new EmployeeEducation();
            objEmpEducation.setEmployee(result);
            objEmpEducation.setDegreeId(employeeEducation.getDegreeId());
            objEmpEducation.setPassingYear(employeeEducation.getPassingYear());
            objEmpEducation.setResult(objEmpEducation.getResult());
            objEmpEducation.setStatus(employee.isStatus());
            objEmpEducation.setCreatedAt(employee.getCreatedAt());
            objEmpEducation.setUpdatedAt(employee.getUpdatedAt());
            objEmpEducation.setDeletedAt(employee.getDeletedAt());
            objEmpEducation.setBrowser(employee.getBrowser());
            objEmpEducation.setIp(employee.getIp());
            employeeEducations.add(objEmpEducation);
        }
        employeeEducationRepository.saveAll(employeeEducations);
        return new MessageResponse(Message.SUCCESS_CREATION);
    }

    @Override
    @Transactional(value = TxType.REQUIRED)
    public MessageResponse updateEmployee(int id, Employee employee) {
        Optional<Employee> result = employeesRepository.findById(id);
        List<EmployeeEducation> employeeEducations = new ArrayList<>();
        if (result.isPresent()) {
            Employee objModifyingEmployee = result.get();
            objModifyingEmployee.setEmployeeType(employee.getEmployeeType());
            objModifyingEmployee.setFirstName(employee.getFirstName());
            objModifyingEmployee.setMiddleInitial(employee.getMiddleInitial());
            objModifyingEmployee.setLastName(employee.getLastName());
            objModifyingEmployee.setDateOfBirth(employee.getDateOfBirth());
            objModifyingEmployee.setBloodGroup(employee.getBloodGroup());
            objModifyingEmployee.setNidNumber(employee.getNidNumber());
            objModifyingEmployee.setSalesOrganization(employee.getSalesOrganization());
            objModifyingEmployee.setMobileNumber(employee.getMobileNumber());
            objModifyingEmployee.setEmail(employee.getEmail());
            objModifyingEmployee.setDesignation(employee.getDesignation());
            objModifyingEmployee.setAddress(employee.getAddress());
            objModifyingEmployee.setDivision(employee.getDivision());
            objModifyingEmployee.setRegion(employee.getRegion());
            objModifyingEmployee.setZone(employee.getZone());
            objModifyingEmployee.setRoute(employee.getRoute());
            objModifyingEmployee.setPostalCode(employee.getPostalCode());
            objModifyingEmployee.setUpazilaId(employee.getUpazilaId());
            objModifyingEmployee.setDistrictId(employee.getDistrictId());
            objModifyingEmployee.setDateOfJoining(employee.getDateOfJoining());
            objModifyingEmployee.setDateOfResignation(employee.getDateOfResignation());
            objModifyingEmployee.setBasicSalary(employee.getBasicSalary());
            objModifyingEmployee.setHouseRent(employee.getHouseRent());
            objModifyingEmployee.setMedicalAllowance(employee.getMedicalAllowance());
            objModifyingEmployee.setInternetBill(employee.getInternetBill());
            objModifyingEmployee.setMobileBill(employee.getMobileBill());
            objModifyingEmployee.setTravellingDailyAllowance(employee.getTravellingDailyAllowance());
            objModifyingEmployee.setMeetingTravellingAllowance(employee.getMeetingTravellingAllowance());
            objModifyingEmployee.setMeetingDailyAllowance(employee.getMeetingDailyAllowance());
            objModifyingEmployee.setCityAllowance(employee.getCityAllowance());
            objModifyingEmployee.setOtherAllowance(employee.getOtherAllowance());
            objModifyingEmployee.setContributionPercentage(employee.getContributionPercentage());
            objModifyingEmployee.setEmergencyContactName(employee.getEmergencyContactName());
            objModifyingEmployee.setEmergencyMobileNumber(employee.getEmergencyMobileNumber());
            objModifyingEmployee.setEmergencyContactRelation(employee.getEmergencyContactRelation());
            objModifyingEmployee.setBank(employee.getBank());
            objModifyingEmployee.setBackAccNumber(employee.getBackAccNumber());
            objModifyingEmployee.setStatus(employee.isStatus());
            objModifyingEmployee.setCreatedAt(employee.getCreatedAt());
            objModifyingEmployee.setUpdatedAt(employee.getUpdatedAt());
            objModifyingEmployee.setDeletedAt(employee.getDeletedAt());
            objModifyingEmployee.setBrowser(employee.getBrowser());
            objModifyingEmployee.setIp(employee.getIp());

            var updatedResult = employeesRepository.save(objModifyingEmployee);

            employeeEducationRepository.deleteByEmployeeId(id);

            for (EmployeeEducation employeeEducation : employee.getEducationList()) {
                EmployeeEducation objEmpEducation = new EmployeeEducation();

                objEmpEducation.setEmployee(updatedResult);
                objEmpEducation.setDegreeId(employeeEducation.getDegreeId());
                objEmpEducation.setPassingYear(employeeEducation.getPassingYear());
                objEmpEducation.setResult(objEmpEducation.getResult());
                objEmpEducation.setStatus(employee.isStatus());
                objEmpEducation.setCreatedAt(employee.getCreatedAt());
                objEmpEducation.setUpdatedAt(employee.getUpdatedAt());
                objEmpEducation.setDeletedAt(employee.getDeletedAt());
                objEmpEducation.setBrowser(employee.getBrowser());
                objEmpEducation.setIp(employee.getIp());
                employeeEducations.add(objEmpEducation);
            }
            employeeEducationRepository.saveAll(employeeEducations);

        } else {
            throw new ResourceNotFoundException("Employee", "id", id);
        }

        return new MessageResponse(Message.EMPLOYEE_UPDATED_SUCCESSFULLY);
    }

    @Override
    @Transactional(value = TxType.REQUIRED)
    public void deleteEmployeeById(int id) {
        employeesRepository.deleteById(id);
    }

    @Override
    public Optional<Employee> updateEmployeeStatus(int id, Employee employee) {
        Optional<Employee> result = employeesRepository.findById(id);
        if (result.isPresent()) {
            Employee employees = result.get();
            employees.setStatus(employees.isStatus());
            employeesRepository.save(employees);
        } else {
            throw new ResourceNotFoundException("Employee", "id", id);
        }

        return result;
    }

    @Override
    public List<Employee> findSortedEmployeeByKey(String field) {
        return employeesRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeesRepository.findAll();
    }

    @Override
    public EmployeeDTO findEmployeeById(int employeeId) {
        EmployeeDTO employeeRequest = new EmployeeDTO();
        List<EmployeeEducation> employeeEducations = employeeEducationRepository
                .findEmployeeEducationByEmployeeId(employeeId);
        Employee employee = employeesRepository.findById(employeeId).get();
        employeeRequest.setBank(employee.getBank());
        employeeRequest.setAddress(employee.getAddress());
        employeeRequest.setFirstName(employee.getFirstName());
        employeeRequest.setLastName(employee.getLastName());
        employeeRequest.setMiddleInitial(employee.getMiddleInitial());

        employeeRequest.setEducationalQualificationList(employeeEducations);
        return employeeRequest;
    }

    @Override
    public List<EmployeeEducation> findEmployeeEducationByEmployeeId(int employeeId) {
        return employeeEducationRepository.findEmployeeEducationByEmployeeId(employeeId);
    }

    @Override
    public List<Employee> findEmployeeByType(EmployeeType employeeType) {
        return employeesRepository.findByEmployeeType(employeeType);
    }

    @Override
    public Employee getEmployeeDetailsById(int employeeId) {
        return employeesRepository.getEmployeeDetailsById(employeeId);
    }
}
