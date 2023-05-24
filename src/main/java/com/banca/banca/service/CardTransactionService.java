package com.banca.banca.service;

import com.banca.banca.dto.CardTransactionDto;
import com.banca.banca.entity.CardTransaction;

import java.util.List;
import java.util.Optional;

public interface CardTransactionService {

    Optional<CardTransaction> findCardTransactionById(Integer id);

    List<CardTransactionDto> getCardTransaction (String fiscalCode);

}
