package com.banca.banca.dto;

import java.util.Date;

/**
 * Classe che utilizzo per mappare i campi che mi servono per fare un bonifico,
 * makeTransfer() come parametro di imput avrà l'oggetto TransferDto
 */
public class TransferDto {

    private String ibanBeneficiary;

    private String description;

    private Double amount;

    private String name;

    private String surname;

    private Date dateTransaction;


    //Constructor
    public TransferDto() {

    }

    public TransferDto(String ibanBeneficiary, String description, Double amount, String name, String surname, Date dateTransaction) {
        this.ibanBeneficiary = ibanBeneficiary;
        this.description = description;
        this.amount = amount;
        this.name = name;
        this.surname = surname;
        this.dateTransaction = dateTransaction;
    }


    //Getter and Setter
    public String getIbanBeneficiary() {
        return ibanBeneficiary;
    }

    public void setIbanBeneficiary(String ibanBeneficiary) {
        this.ibanBeneficiary = ibanBeneficiary;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public Date getDateTransaction() { return dateTransaction; }

    public void setDateTransaction(Date dateTransaction) { this.dateTransaction = dateTransaction; }
}
