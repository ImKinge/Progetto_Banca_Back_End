package com.banca.banca.mapper;

import com.banca.banca.dto.CardDto;
import com.banca.banca.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CardMapperImpl implements CardMapper{

    @Autowired
    CurrentAccountMapper currentAccountMapper;

    @Override
    public CardDto toCardDto(Card card) {

        CardDto cardDto = new CardDto();

        cardDto.setCardNumber(card.getCardNumber());
        cardDto.setBalance(card.getBalance());
        cardDto.setType(card.getType());
        cardDto.setIbanNumber(currentAccountMapper.toCurrentAccountDto((card.getCurrentAccount())));

        return cardDto;
    }

    @Override
    public Card toCard(CardDto cardDto) {

        Card card = new Card();

        card.setCardNumber(cardDto.getCardNumber());
        card.setBalance(cardDto.getBalance());
        card.setType(cardDto.getType());
        card.setCurrentAccount(currentAccountMapper.toCurrentAccount(cardDto.getIbanNumber()));

        return card;
    }

    @Override
    public List<CardDto> toListCardDto(List<Card> cardList) {

        if(cardList == null) {
            return null;
        }

        List<CardDto> cardDtoList = new ArrayList<>(cardList.size());

        for (Card card: cardList) {
            cardDtoList.add(toCardDto(card));
        }

        return cardDtoList;
    }
}
