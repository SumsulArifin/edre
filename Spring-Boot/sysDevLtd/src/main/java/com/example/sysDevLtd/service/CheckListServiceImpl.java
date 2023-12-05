package com.example.sysDevLtd.service;

import com.example.sysDevLtd.entity.model.CheckList;
import com.example.sysDevLtd.entity.dto.CheckListDTO;
import com.example.sysDevLtd.repository.CheckListRepository;
import com.example.sysDevLtd.utils.Message;
import com.example.sysDevLtd.utils.MessageResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;
import org.springframework.util.ResourceUtils;



@Service
public class CheckListServiceImpl implements CheckListService{
    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    CheckListRepository checkListRepository;

    @Override
    public MessageResponse saveCheckList(CheckListDTO checkListDTO) {
        CheckList checkList = new CheckList();
        checkList.setDetails(checkListDTO.getDetails());
        checkList.setRemark(checkListDTO.getRemark());
        checkListRepository.save(checkList);
        return new MessageResponse(Message.SUCCESS);
    }

    @Override
    public List<CheckList> getAllCheckList() {
        return checkListRepository.findAll();
    }

    @Override
    public CheckList findCheckListById(int sl) {
        return checkListRepository.findById(sl).get();
    }

    @Override
    public Optional<CheckList> updateCheckList(int sl, CheckListDTO employeeDTO) {
//        Optional<Employee> result = employeeRepository.findById(empId);
//
//        if (result.isPresent()) {
//            Employee employee = result.get();
//            employee.setName(employeeDTO.getName());
//            employee.setCode(employeeDTO.getCode());
//            employee.setDob(employeeDTO.getDob());
//            employee.setGender(employeeDTO.getGender());
//            employee.setMobile(employeeDTO.getMobile());
//            employee.setDepartment(employeeDTO.getDepartment());
//            employeeRepository.save(employee);
//        } else {
//            throw new ResourceNotFoundException("Employee", "empId", empId);
//        }
//
//        return result;.
        return null;
    }

    @Override
    public void deleteCheckListById(int sl) {
        checkListRepository.deleteById(sl);

    }

    @Override
    public void saveChecklists(List<CheckList> checkLists) {
        checkListRepository.saveAll(checkLists);
    }
    @Override
    public String generateAndSaveReport(List<CheckList> checkLists) {
        try {
            // Load the JRXML template file
            File templateFile = ResourceUtils.getFile("classpath:checklist_template.jrxml");

            // Compile the JRXML template file
            JasperReport jasperReport = JasperCompileManager.compileReport(templateFile.getAbsolutePath());

            // Convert the CheckList data into JRBeanCollectionDataSource
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(checkLists);

            // Fill the JasperReport template with data
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);

            // Save the generated report to a PDF file
            String outputFilePath = "C:\\Users\\user\\Desktop\\Report\\checklist_report.pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, outputFilePath);

            return outputFilePath; // Return the file path where the report is saved
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception or return a default file path indicating failure
            return "Error occurred while generating the report.";
        }
    }
}
