package com.banca.banca.repository;

import com.banca.banca.entity.DetailsCustomerServiceBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailsCustomerServiceBankRepository extends JpaRepository <DetailsCustomerServiceBank, Integer> {

    @Query(value = "select i from DetailsCustomerServiceBank i  where i.customerData.fiscalCode=?1")
    List<DetailsCustomerServiceBank> findAllByFiscalCode (String fiscalCode);

}
