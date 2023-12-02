package com.tkgroupbd.pusti.api.data.repositories.mastersettings.banks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tkgroupbd.pusti.api.data.models.entity.mastersettings.banks.Bank;

import java.util.List;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {
    @Query("SELECT b FROM Bank b WHERE LOWER(b.bankName) LIKE LOWER(CONCAT('%', :name, '%'))  ORDER BY b.bankName ASC")
    List<Bank> findByBankNameContaining(String name);
}
