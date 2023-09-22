package com.banca.banca.dto;

import java.time.LocalDateTime;

public class ReportTransactionResponseDto {

    private String Description;

    private LocalDateTime dateTransaction;

    private String amount;


    public ReportTransactionResponseDto() {

    }

    public ReportTransactionResponseDto(String description, LocalDateTime dateTransaction, String amount) {
        Description = description;
        this.dateTransaction = dateTransaction;
        this.amount = amount;
    }


    //Getter and Setter

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }


    public LocalDateTime getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(LocalDateTime dateTransaction) {
        this.dateTransaction = dateTransaction;
    }


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
