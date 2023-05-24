package com.banca.banca.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Table(name = "iban_transaction", schema = "banca")
@Entity
public class IbanTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Identity permette di generare il dato auto increment
    private Integer id;

    @Column(nullable = false)
    private Double amount;

    @Column(name = "date_transaction", nullable = false)
    private LocalDateTime dateTransaction;

    @Column(name = "description_transaction", nullable = false)
    private String descriptionTransaction;

    @ManyToOne
    @JoinColumn(name = "orderer", referencedColumnName = "fiscal_code", nullable = false)
    private CustomerData customerDataOr;

    @ManyToOne
    @JoinColumn(name = "beneficiary",referencedColumnName = "fiscal_code", nullable = false)
    private CustomerData customerDataBf;


    //Constructor
    public IbanTransaction() {}

    public IbanTransaction(Integer id, Double amount, LocalDateTime dateTransaction, String descriptionTransaction, CustomerData customerDataOr, CustomerData customerDataBf) {
        this.id = id;
        this.amount = amount;
        this.dateTransaction = dateTransaction;
        this.descriptionTransaction = descriptionTransaction;
        this.customerDataOr = customerDataOr;
        this.customerDataBf = customerDataBf;
    }


    //Getter and Setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
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

    public CustomerData getCustomerDataOr() {
        return customerDataOr;
    }

    public void setCustomerDataOr(CustomerData customerDataOr) {
        this.customerDataOr = customerDataOr;
    }

    public CustomerData getCustomerDataBf() {
        return customerDataBf;
    }

    public void setCustomerDataBf(CustomerData customerDataBf) {
        this.customerDataBf = customerDataBf;
    }
}
