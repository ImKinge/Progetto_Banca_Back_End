package com.banca.banca.dto;

import com.banca.banca.entity.CustomerData;
import com.banca.banca.entity.ServiceBank;

import java.time.LocalDateTime;

public class DetailsCustomerServiceBankDto {

    private Integer id;

    private ServiceBank serviceBank;

    private CustomerData customerData;

    private LocalDateTime purchaseDate;

    public DetailsCustomerServiceBankDto () {

    }



    public DetailsCustomerServiceBankDto(Integer id, ServiceBank serviceBank, CustomerData customerData, LocalDateTime purchaseDate) {
        this.id = id;
        this.serviceBank = serviceBank;
        this.customerData = customerData;
        this.purchaseDate = purchaseDate;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ServiceBank getServiceBank() {
        return serviceBank;
    }

    public void setServiceBank(ServiceBank serviceBank) {
        this.serviceBank = serviceBank;
    }

    public CustomerData getCustomerData() {
        return customerData;
    }

    public void setCustomerData(CustomerData customerData) {
        this.customerData = customerData;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
