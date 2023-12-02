package com.tkgroupbd.pusti.api.services.mastersettings.banks;
import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.banks.Bank;
import com.tkgroupbd.pusti.api.data.payloads.dto.mastersettings.banks.BankRequest;
import com.tkgroupbd.pusti.api.data.payloads.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public interface BankService {
    MessageResponse addBank(BankRequest bankRequest);

    Optional<Bank> updateBank(Integer id, BankRequest bankRequest);

    void deleteBank(Integer id);

    Bank getBankById(Integer id);

    List<Bank> getAllBanks();

    List<Bank> findSortedBanksByKey(String field);

    Page<Bank> findBankByPagination(int offset, int pageSize);

    Page<Bank> findSortedBankByPagination(int offset, int pageSize, String field);

    List<Bank> findBankByBankName(String name);

    Optional<Bank> bankStatusChange(Integer id, BankRequest bankRequestRequest);

}
