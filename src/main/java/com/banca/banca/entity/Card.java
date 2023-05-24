package com.banca.banca.entity;

import jakarta.persistence.*;

@Table(name = "card", schema = "banca")
@Entity
public class Card {

    @Id
    @Column(name = "card_number", length = 16)
    private String cardNumber;

    private Double balance;

    private String type;

    @ManyToOne
    @JoinColumn(name = "iban_number", referencedColumnName = "iban_number", nullable = false)
    private CurrentAccount currentAccount;


    //Constructor
    public Card() {

    }

    public Card(String cardNumber, Double balance, String type, CurrentAccount currentAccount) {
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.type = type;
        this.currentAccount = currentAccount;
    }


    //Getter and Setter
    public String getCardNumber() {
        return this.cardNumber;
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


    public CurrentAccount getCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(CurrentAccount currentAccount) {
        this.currentAccount = currentAccount;
    }
}
