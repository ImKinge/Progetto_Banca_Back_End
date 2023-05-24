package com.banca.banca.entity;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "current_account", schema = "banca")
@Entity
public class CurrentAccount {

    @Id
    @Column(name = "iban_number", length = 27)
    private String ibanNumber;

    private Double balance;

    @OneToMany(mappedBy = "currentAccount")
    private List<Card> cards;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer", referencedColumnName = "fiscal_code")
    private CustomerData customerData;


    //Constructor
    public CurrentAccount() {

    }

    public CurrentAccount(String ibanNumber, Double balance, List<Card> cards, CustomerData customerData) {
        this.ibanNumber = ibanNumber;
        this.balance = balance;
        this.cards = cards;
        this.customerData = customerData;
    }

    //Getter and Setter
    public String getIbanNumber() {
        return ibanNumber;
    }

    public void setIbanNumber(String ibanNumber) {
        this.ibanNumber = ibanNumber;
    }


    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }


    public CustomerData getCustomerData() {
        return customerData;
    }

    public void setCustomerData(CustomerData customerData) {
        this.customerData = customerData;
    }


    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
