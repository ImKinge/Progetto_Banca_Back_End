package com.banca.banca.dto;

import com.banca.banca.entity.CustomerData;

import java.io.Serializable;
import java.time.LocalDateTime;


//Serializable serve per dire che quell'oggetto verrà clonato, che non è altro che il processo di serializzazione
public class CardTransactionDto implements Serializable {

    private String amount;

    private LocalDateTime dateTransaction;

    private String descriptionTransaction;

    //Chi fa il pagamento carta
    private CustomerDataDto orderer;

    //Chi riceve il rimborso
    private CustomerDataDto beneficiary;


    //Constructor
    public CardTransactionDto () {}

    public CardTransactionDto(String amount, LocalDateTime dateTransaction, String descriptionTransaction, CustomerDataDto orderer, CustomerDataDto beneficiary) {
        this.amount = amount;
        this.dateTransaction = dateTransaction;
        this.descriptionTransaction = descriptionTransaction;
        this.orderer = orderer;
        this.beneficiary = beneficiary;
    }

    //Getter and Setter
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


    public LocalDateTime getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(LocalDateTime dateTransaction) {
        this.dateTransaction = dateTransaction;
    }


    public String getDescriptionTransaction() {
        return descriptionTransaction;
    }

    public void setDescriptionTransaction(String descriptionTransaction) {
        this.descriptionTransaction = descriptionTransaction;
    }


    public CustomerDataDto getOrderer() {
        return orderer;
    }

    public void setOrderer(CustomerDataDto orderer) {
        this.orderer = orderer;
    }


    public CustomerDataDto getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(CustomerDataDto beneficiary) {
        this.beneficiary = beneficiary;
    }
}
