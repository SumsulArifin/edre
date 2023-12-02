package com.tkgroupbd.pusti.api.services.mastersettings.banks;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.banks.Bank;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.banks.BankRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import com.tkgroupbd.pusti.api.data.repositories.mastersettings.banks.BankRepository;
import com.tkgroupbd.pusti.api.exceptions.ResourceNotFoundException;
import com.tkgroupbd.pusti.api.utils.constant.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    @SuppressWarnings(Message.AUTOWIRED_OK)
    private BankRepository bankRepository;

    @Override
    public MessageResponse addBank(BankRequest bankRequest) {
        try {
            Bank newBank = new Bank();
            newBank.setBankName(bankRequest.getBankName());
            newBank.setBankAddress(bankRequest.getBankAddress());
            newBank.setStatus(bankRequest.isStatus());
            newBank.setAccountant(bankRequest.getAccountant());
            newBank.setContactNumber(bankRequest.getContactNumber());
            newBank.setDistributor(bankRequest.getDistributor());
            bankRepository.save(newBank);
            return new MessageResponse(Message.SUCCESS_BANK_CREATION);
        } catch (Exception e) {
            return new MessageResponse(Message.FAILED_CREATION + e.getMessage() + " The cause is : " + e.getCause());
        }

    }

    @Override
    public Optional<Bank> updateBank(Integer id, BankRequest bankRequest) {

        Optional<Bank> result = bankRepository.findById(id);
        if (result.isPresent()) {
            Bank bank = result.get();
            bank.setBankName(bankRequest.getBankName());
            bank.setStatus(bankRequest.isStatus());
            bank.setAccountant(bankRequest.getAccountant());
            bank.setContactNumber(bankRequest.getContactNumber());
            bank.setDistributor(bankRequest.getDistributor());
            bankRepository.save(bank);
        } else {
            throw new ResourceNotFoundException("Bank", "id", id);
        }
        return result;
    }

    @Override
    public void deleteBank(Integer id) {
        bankRepository.deleteById(id);

    }

    @Override
    public Bank getBankById(Integer id) {
        return bankRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bank", "id", id));
    }

    @Override
    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    @Override
    public List<Bank> findSortedBanksByKey(String field) {
        return bankRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    @Override
    public Page<Bank> findBankByPagination(int offset, int pageSize) {
        Page<Bank> banks = bankRepository.findAll(PageRequest.of(offset, pageSize));
        return banks;
    }

    @Override
    public Page<Bank> findSortedBankByPagination(int offset, int pageSize, String field) {
        Page<Bank> banks = bankRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return banks;
    }

    @Override
    public List<Bank> findBankByBankName(String name) {
        List<Bank> banksName = bankRepository.findByBankNameContaining(name);
        return banksName;
    }

    @Override
    public Optional<Bank> bankStatusChange(Integer id, BankRequest bankRequest) {
        Optional<Bank> result = bankRepository.findById(id);
        if (result.isPresent()) {
            Bank bank = result.get();
            bank.setStatus(bankRequest.isStatus());
            bankRepository.save(bank);
        } else {
            throw new ResourceNotFoundException("Bank", "id", id);
        }
        return result;
    }




}
