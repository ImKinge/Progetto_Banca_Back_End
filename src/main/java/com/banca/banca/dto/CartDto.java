package com.banca.banca.dto;

import com.banca.banca.entity.CustomerData;
import com.banca.banca.entity.ServiceBank;

public class CartDto {

    private Integer id;

    private ServiceBank serviceBank;

    private CustomerData customerData;

    public CartDto () {

    }

    public CartDto(Integer id, ServiceBank serviceBank, CustomerData customerData) {
        this.id = id;
        this.serviceBank = serviceBank;
        this.customerData = customerData;
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
}
