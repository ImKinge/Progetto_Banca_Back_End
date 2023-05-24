package com.banca.banca.dto;

import com.banca.banca.entity.CustomerData;

import java.util.List;

public class CurrentAccountDto {

    private String ibanNumber;

    private Double balance;

    private CustomerDataDto customerData;


    //Constructor
    public CurrentAccountDto() {
    }

    public CurrentAccountDto(String ibanNumber, Double balance, CustomerDataDto fiscalCode) {
        this.ibanNumber = ibanNumber;
        this.balance = balance;
        this.customerData = fiscalCode;
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


    public CustomerDataDto getFiscalCode() {
        return customerData;
    }

    public void setFiscalCode(CustomerDataDto fiscalCode) {
        this.customerData = fiscalCode;
    }
}
