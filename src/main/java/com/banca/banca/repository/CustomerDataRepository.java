package com.banca.banca.repository;

import com.banca.banca.entity.CustomerData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerDataRepository extends JpaRepository <CustomerData, String> {

    Optional<CustomerData> findByFiscalCode(String fiscalCode);

    Optional<CustomerData> findByUserName(String username);

    Optional<CustomerData> findByUserNameAndPassword (String username, String password);

    //Se esiste oopure no
    Boolean existsByUserName(String userName);
}
