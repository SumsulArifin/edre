package com.tkgroupbd.pusti.api.controllers.mastersettings.banks;

import com.tkgroupbd.pusti.api.data.models.common.ApiResponse;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.banks.Bank;
import com.tkgroupbd.pusti.api.data.models.entity.systemsettings.AlternateChannelSettings;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.banks.BankRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.services.mastersettings.banks.BankService;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Tag(name = "Bank")
@RestController
@RequestMapping("/bank")
@CrossOrigin(origins = { "*" })
public class BankController {

    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    BankService bankService;

    @GetMapping("/getAllBanks")
    public ResponseEntity<List<Bank>> getAllBanks() {
        List<Bank> banks = bankService.getAllBanks();
        return new ResponseEntity<>(banks, HttpStatus.OK);
    }

    @PostMapping("/addNewBank")
    public ResponseEntity<MessageResponse> addBank(@RequestBody @Valid BankRequest bankRequest) {

        MessageResponse newBank = bankService.addBank(bankRequest);
        return new ResponseEntity<>(newBank, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteBank/{id}")
    public ResponseEntity<?> deleteBank(@PathVariable("id") Integer id) {
        bankService.deleteBank(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getBankById/{id}")
    public ResponseEntity<Bank> getBankById(@PathVariable("id") Integer id) {
        Bank bank = bankService.getBankById(id);
        return new ResponseEntity<>(bank, HttpStatus.OK);
    }

    @PutMapping("/updateBank/{id}")
    public ResponseEntity<Optional<Bank>> updateBank(@PathVariable Integer id,
            @RequestBody @Valid BankRequest bankRequest) {
        Optional<Bank> updateBank = bankService.updateBank(id, bankRequest);
        return new ResponseEntity<Optional<Bank>>(updateBank, HttpStatus.OK);
    }

    @PutMapping("/updateBankStatus/{id}")
    public ResponseEntity<Optional<Bank>> updateBankStatus(@PathVariable Integer id,
            @RequestBody @Valid BankRequest bankRequest) {
        Optional<Bank> updateBank = bankService.bankStatusChange(id, bankRequest);
        return new ResponseEntity<Optional<Bank>>(updateBank, HttpStatus.OK);
    }

//    @GetMapping("/bankSortedFor/{field}")
//    private ApiResponse<List<Bank>> getBankSortedFor(@PathVariable String field) {
//        List<Bank> bankList = bankService.findSortedBanksByKey(field);
//        return new ApiResponse(bankList.size(), bankList);
//    }

    @GetMapping("/getBankByName/{name}")
    private List<Bank> findBankByName(@PathVariable String name) {
        List<Bank> allBanks = bankService.findBankByBankName(name);
        return allBanks;
    }
    @GetMapping("/getPaginatedBanks")
    private Page<Bank> getBankByPagination(
            @RequestParam("offset") int offset,
            @RequestParam("pageSize") int pageSize
    ) {
        Page<Bank> banks = bankService.findBankByPagination(offset, pageSize);
        return banks;
    }

}