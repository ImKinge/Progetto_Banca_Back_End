package com.banca.banca.mapper;

import com.banca.banca.dto.CardDto;
import com.banca.banca.entity.Card;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardMapper {

    CardDto toCardDto (Card card);

    Card toCard (CardDto cardDto);

    List<CardDto> toListCardDto (List<Card> cardList);
}
