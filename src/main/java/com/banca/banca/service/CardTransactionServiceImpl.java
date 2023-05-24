package com.banca.banca.service;

import com.banca.banca.dto.CardTransactionDto;
import com.banca.banca.entity.CardTransaction;
import com.banca.banca.entity.IbanTransaction;
import com.banca.banca.mapper.CardTransactionMapper;
import com.banca.banca.repository.CardTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

@Service
public class CardTransactionServiceImpl  implements CardTransactionService{

    @Autowired
    private CardTransactionRepository cardTransactionRepository;

    @Autowired
    private CardTransactionMapper cardTransactionMapper;


    @Override
    public Optional<CardTransaction> findCardTransactionById(Integer id) {
        return cardTransactionRepository.findById(id);
    }

    @Override
    public List<CardTransactionDto> getCardTransaction(String fiscalCode) {
        List<CardTransaction> cardTransactionList = cardTransactionRepository.findAllByCustomerDataOr_FiscalCode(fiscalCode);

        for (CardTransaction cardTransaction : cardTransactionList) {
            if (fiscalCode.equals(cardTransaction.getCustomerDataOr().getFiscalCode())) {
                cardTransaction.setAmount(cardTransaction.getAmount() * -1);
            }
        }

        List<CardTransactionDto> cardTransactionDtoList = cardTransactionMapper.toListCardTransactionDto(cardTransactionList);

        return cardTransactionDtoList;

    }

}
