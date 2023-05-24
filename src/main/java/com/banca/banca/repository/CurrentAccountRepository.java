package com.banca.banca.repository;

import com.banca.banca.dto.CurrentAccountDto;
import com.banca.banca.entity.CurrentAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CurrentAccountRepository extends JpaRepository <CurrentAccount, String> {

    @Query(value = "select i from CurrentAccount i where i.customerData.fiscalCode=?1")
    Optional<CurrentAccount> findByFiscalCode (String fiscalCode);

    Optional<CurrentAccount> findByIbanNumber (String ibanNumber);

    List<CurrentAccount> findAllByIbanNumber (String ibanNumber);
}
