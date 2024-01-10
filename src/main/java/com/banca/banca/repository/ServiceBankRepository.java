package com.banca.banca.repository;

import com.banca.banca.entity.ServiceBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceBankRepository extends JpaRepository <ServiceBank, Integer> {

    Optional<ServiceBank> findById (Integer id);
}
