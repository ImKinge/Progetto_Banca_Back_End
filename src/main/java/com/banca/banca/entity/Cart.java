package com.banca.banca.entity;

import jakarta.persistence.*;


@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private ServiceBank serviceBank;

    @OneToOne
    private CustomerData customerData;


    public Cart() {
    }

    public Cart(ServiceBank serviceBank, CustomerData customerData) {
        this.serviceBank = serviceBank;
        this.customerData = customerData;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
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
