package com.banca.banca.dto;

import com.banca.banca.entity.CurrentAccount;

public class CardDto {

    private String cardNumber;

    private Double balance;

    private String type;

    private CurrentAccountDto ibanNumber;


    //Constructor
    public CardDto() {
    }

    public CardDto(String cardNumber, Double balance, String type, CurrentAccountDto ibanNumber) {
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.type = type;
        this.ibanNumber = ibanNumber;
    }


    //Getter and Setter
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }


    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public CurrentAccountDto getIbanNumber() {
        return ibanNumber;
    }

    public void setIbanNumber(CurrentAccountDto ibanNumber) {
        this.ibanNumber = ibanNumber;
    }
}
