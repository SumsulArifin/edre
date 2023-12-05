package com.example.sysDevLtd.service;

import com.example.sysDevLtd.entity.model.CheckList;
import com.example.sysDevLtd.entity.dto.CheckListDTO;
import com.example.sysDevLtd.utils.MessageResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface CheckListService {
    public MessageResponse saveCheckList(CheckListDTO checkListDTO);
    public List<CheckList> getAllCheckList();
    public CheckList findCheckListById(int sl);
    public Optional<CheckList> updateCheckList(int sl, CheckListDTO employeeDTO);
    public void deleteCheckListById(int sl);

    public void saveChecklists(List<CheckList> checkLists);
    public String generateAndSaveReport(List<CheckList> checkLists);
}
