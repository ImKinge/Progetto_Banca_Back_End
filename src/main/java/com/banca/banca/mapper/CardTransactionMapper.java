package com.banca.banca.mapper;

import com.banca.banca.dto.CardTransactionDto;
import com.banca.banca.entity.CardTransaction;

import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CardTransactionMapper {

    public CardTransactionDto toCardTransactionDto (CardTransaction cardTransaction);

    public CardTransaction toCardTransaction (CardTransactionDto cardTransactionDto);

    List<CardTransactionDto> toListCardTransactionDto (List<CardTransaction> cardTransactionList);

}
