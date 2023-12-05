package com.example.sysDevLtd.restController;

import com.example.sysDevLtd.entity.model.CheckList;
import com.example.sysDevLtd.entity.dto.CheckListDTO;
import com.example.sysDevLtd.service.CheckListService;
import com.example.sysDevLtd.utils.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
@CrossOrigin(originPatterns = {"*"})
public class CheckListController {

    @Autowired
    CheckListService checkListService;

    @PostMapping("/addNewCheckList")
    public ResponseEntity<MessageResponse> saveCheckList(@RequestBody CheckListDTO checkListDTO) {
        MessageResponse response = checkListService.saveCheckList(checkListDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/multipleCheckList")
    public ResponseEntity<String> saveMultipleChecklists(@RequestBody List<CheckList> checkListList) {
        checkListService.saveChecklists(checkListList);
        return new ResponseEntity<>("Checklists saved successfully", HttpStatus.CREATED);
    }

    // retrieve all CheckList
    @GetMapping("/getAllCheckList")
    @ResponseBody
    public ResponseEntity<List<CheckList>> getAllCheckList() {
        List<CheckList> checkLists = checkListService.getAllCheckList();
        return new ResponseEntity<>(checkLists, HttpStatus.OK);
    }

    // Update single CheckList information
    @PutMapping("/updateCheckList/{sl}")
    public ResponseEntity<Optional<CheckList>> updateCheckList(@PathVariable int sl, @RequestBody CheckListDTO checkListDTO) {
        Optional<CheckList> checkList = checkListService.updateCheckList(sl, checkListDTO);
        return new ResponseEntity<Optional<CheckList>>(checkList, HttpStatus.OK);
    }


    // Delete CheckList by id
    @DeleteMapping("/deleteCheckList/{sl}")
    public ResponseEntity<?> deleteCheckListById(@PathVariable("sl") int sl) {
        checkListService.deleteCheckListById(sl);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // API to retrieve CheckList by id
    @GetMapping("/getCheckListById/{sl}")
    public ResponseEntity<CheckList> getCheckListById(@PathVariable("sl") int sl) {
        CheckList checkList = checkListService.findCheckListById(sl);
        return new ResponseEntity<>(checkList, HttpStatus.OK);
    }

    @GetMapping("/report/generate")
    public ResponseEntity<Resource> generateReportAndDownload() {
        try {
            List<CheckList> checkLists = checkListService.getAllCheckList();
            ;

            String filePath = checkListService.generateAndSaveReport(checkLists);

            // Provide a downloadable link
            Path path = Paths.get(filePath);
            Resource resource = new UrlResource(path.toUri());

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
    }

