package com.banca.banca.service;

import com.banca.banca.dto.CardDto;
import com.banca.banca.exception.CardException;

import java.util.List;

public interface CardService {

    CardDto findByFiscalCode (String token) throws CardException;

    List<CardDto> findListByCardNumber (String cardNumber);
}
