package com.banca.banca.repository;

import com.banca.banca.entity.CardTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CardTransactionRepository extends JpaRepository <CardTransaction, Integer> {

    Optional<CardTransaction> findById(Integer id);

    /*
    Il trattino basso ha permesso di matchare l'ogetto del CustomerData all'ordinario della ibanTransazione
     */
    List<CardTransaction> findAllByCustomerDataOr_FiscalCode (String fiscalCode);
}
