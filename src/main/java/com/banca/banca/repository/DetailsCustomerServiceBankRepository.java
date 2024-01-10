package com.banca.banca.repository;

import com.banca.banca.entity.DetailsCustomerServiceBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsCustomerServiceBankRepository extends JpaRepository <DetailsCustomerServiceBank, Integer> {

}
