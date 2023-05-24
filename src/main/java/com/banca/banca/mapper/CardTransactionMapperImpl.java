package com.banca.banca.mapper;

import com.banca.banca.dto.CardTransactionDto;
import com.banca.banca.dto.IbanTransactionDto;
import com.banca.banca.entity.CardTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class CardTransactionMapperImpl implements CardTransactionMapper {

    @Autowired
    CustomerDataMapper customerDataMapper;

    @Override
    public CardTransactionDto toCardTransactionDto(CardTransaction cardTransaction) {

        if ( cardTransaction == null ) {
            return null;
        }

        double value = cardTransaction.getAmount();
        String decimalFormat  = new DecimalFormat("#.00").format(value);//per inserire 2 cifre decimali dopo la virgola se il double fosse una cifra intera
        CardTransactionDto cardTransactionDto = new CardTransactionDto();

        cardTransactionDto.setOrderer(customerDataMapper.toCustomerDataDto(cardTransaction.getCustomerDataOr()));
        cardTransactionDto.setAmount(decimalFormat);
        cardTransactionDto.setDateTransaction(cardTransaction.getDateTransaction());
        cardTransactionDto.setDescriptionTransaction(cardTransaction.getDescriptionTransaction());
        cardTransactionDto.setBeneficiary(customerDataMapper.toCustomerDataDto(cardTransaction.getCustomerDataBf()));

        return cardTransactionDto;
    }

    @Override
    public CardTransaction toCardTransaction(CardTransactionDto cardTransactionDto) {

         /*
        Optional è utilizzato per un valore che può o non può essere presente.
        Quindi un oggetto Optional può contenere un valore non nullo, quindi presente
        oppure può contenere un valore nullo, quindi vuoto
         */
//        Optional<CustomerData> customerDataOr = customerDataService.findByFiscalCode(cardTransactionDto.getOrderer());
//        Optional<CustomerData> customerDataBf = customerDataService.findByFiscalCode(cardTransactionDto.getBeneficiary());

        CardTransaction cardTransaction = new CardTransaction();

        cardTransaction.setAmount(Double.parseDouble(cardTransactionDto.getAmount()));
        cardTransaction.setDescriptionTransaction(cardTransactionDto.getDescriptionTransaction());
        cardTransaction.setCustomerDataOr(customerDataMapper.toCustomerData(cardTransactionDto.getOrderer()));
        cardTransaction.setCustomerDataBf(customerDataMapper.toCustomerData(cardTransactionDto.getBeneficiary()));

        return cardTransaction;
    }

    @Override
    public List<CardTransactionDto> toListCardTransactionDto(List<CardTransaction> cardTransactionList) {

        if (cardTransactionList == null) {

            return null;
        }

        List<CardTransactionDto> cardTransactionDtoList = new ArrayList<>(cardTransactionList.size());

        for(CardTransaction cardTransaction : cardTransactionList) {
            cardTransactionDtoList.add(toCardTransactionDto(cardTransaction));
        }

        return cardTransactionDtoList;
    }
}
