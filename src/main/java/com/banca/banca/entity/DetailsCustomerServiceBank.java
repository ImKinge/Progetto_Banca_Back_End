package com.banca.banca.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "details_customer_service_bank")
public class DetailsCustomerServiceBank {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "service_bank_id")
    private ServiceBank serviceBank;

    @ManyToOne
    @JoinColumn(name = "customer_fiscal_code")
    private CustomerData customerData;

    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;


    public DetailsCustomerServiceBank () {

    }

    public DetailsCustomerServiceBank(Integer id, ServiceBank serviceBank, CustomerData customerData, LocalDateTime purchaseDate) {
        this.id = id;
        this.serviceBank = serviceBank;
        this.customerData = customerData;
        this.purchaseDate = purchaseDate;
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

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
