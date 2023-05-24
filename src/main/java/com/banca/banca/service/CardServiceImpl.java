package com.banca.banca.service;

import com.banca.banca.dto.CardDto;
import com.banca.banca.entity.Card;
import com.banca.banca.exception.CardException;
import com.banca.banca.mapper.CardMapper;
import com.banca.banca.repository.CardRepository;
import com.banca.banca.security.jwt.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CardServiceImpl implements CardService{

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private JWTGenerator jwtGenerator;

    @Autowired
    private CardMapper cardMapper;


    @Override
    public CardDto findByFiscalCode(String token) throws CardException {
        String fiscalCode = jwtGenerator.getFiscalCodeFromJWT(token);
        Card card = cardRepository.findByFiscalCode(fiscalCode).orElseThrow(() -> new CardException("Nessuna carta trovata con fiscal code: " + token));

        return cardMapper.toCardDto(card);
    }

    @Override
    public List<CardDto> findListByCardNumber(String cardNumber) {
        return null;
    }
}
