package com.example.sysDevLtd.restController;
import com.example.sysDevLtd.entity.dto.DepartmentDTO;
import com.example.sysDevLtd.entity.model.Department;
import com.example.sysDevLtd.service.DepartmentService;
import com.example.sysDevLtd.utils.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/department")
@CrossOrigin(originPatterns = {"*"})
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    @PostMapping("/addNewDepartment")
    public ResponseEntity<MessageResponse> saveDepartment(@RequestBody DepartmentDTO departmentDTO) {
        MessageResponse response = departmentService.saveDepartment(departmentDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // retrieve all
    @GetMapping("/getAllDepartment")
    @ResponseBody
    public ResponseEntity<List<Department>> getAllDepartment() {
        List<Department> departments = departmentService.getAllDepartment();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    // Update single Department information
    @PutMapping("/updateDepartment/{deptId}")
    public ResponseEntity<Optional<Department>> updateDepartment(@PathVariable int deptId, @RequestBody DepartmentDTO departmentDTO) {
        Optional<Department> department = departmentService.updateDepartment(deptId, departmentDTO);
        return new ResponseEntity<Optional<Department>>(department, HttpStatus.OK);
    }


    // Delete CheckList by id
    @DeleteMapping("/deleteDepartment/{deptId}")
    public ResponseEntity<?> deleteDepartmentById(@PathVariable("deptId") int deptId) {
        departmentService.deleteDepartmentById(deptId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // API to retrieve CheckList by id
    @GetMapping("/getDepartmentById/{deptId}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("deptId") int deptId) {
        Department department = departmentService.findDepartmentById(deptId);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }
}
