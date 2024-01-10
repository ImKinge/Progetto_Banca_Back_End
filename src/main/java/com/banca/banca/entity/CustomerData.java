package com.banca.banca.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "customer_data", schema = "banca")
@Entity
public class CustomerData {

    @Id
    @Column(name="fiscal_code", nullable = false, length = 16)
    private String fiscalCode;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String email;

    @Column(name = "info_phone", nullable = false)
    private Long infoPhone;

    @Column(nullable = false)
    private String address;

    @Column(name = "username", nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;


    //Relation
    @OneToMany(mappedBy = "customerDataOr")
    private List<CardTransaction> cardTransactionOr = new ArrayList<>();

    @OneToMany(mappedBy = "customerDataBf")
    private List<CardTransaction> cardTransactionBf = new ArrayList<>();

    @OneToMany(mappedBy = "customerDataOr")
    private List<IbanTransaction> ibanTransactionsOr = new ArrayList<>();

    @OneToMany(mappedBy = "customerDataBf")
    private List<IbanTransaction> ibanTransactionsBf = new ArrayList<>();

    @OneToMany(mappedBy = "customerData")
    private List<DetailsCustomerServiceBank> detailsCustomerServiceBankList = new ArrayList<>();

    @OneToMany(mappedBy = "customerData")
    private List<CurrentAccount> currentAccountList;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "customer_roles",
            joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "fiscal_code"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();


    //Constructor
    public CustomerData() {}

    public CustomerData(String fiscalCode, String name, String surname, LocalDate dateOfBirth, String email, Long infoPhone, String address, String userName, String password, List<CardTransaction> cardTransactionOr, List<CardTransaction> cardTransactionBf, List<IbanTransaction> ibanTransactionsOr, List<IbanTransaction> ibanTransactionsBf, List<DetailsCustomerServiceBank> detailsCustomerServiceBankList, List<CurrentAccount> currentAccountList, List<Role> roles) {
        this.fiscalCode = fiscalCode;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.infoPhone = infoPhone;
        this.address = address;
        this.userName = userName;
        this.password = password;
        this.cardTransactionOr = cardTransactionOr;
        this.cardTransactionBf = cardTransactionBf;
        this.ibanTransactionsOr = ibanTransactionsOr;
        this.ibanTransactionsBf = ibanTransactionsBf;
        this.detailsCustomerServiceBankList = detailsCustomerServiceBankList;
        this.currentAccountList = currentAccountList;
        this.roles = roles;
    }

    //Getter and Setter
    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
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


    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Long getInfoPhone() {
        return infoPhone;
    }

    public void setInfoPhone(Long infoPhone) {
        this.infoPhone = infoPhone;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }


    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


    public List<CurrentAccount> getCurrentAccountList() {
        return currentAccountList;
    }

    public void setCurrentAccountList(List<CurrentAccount> currentAccountList) {
        this.currentAccountList = currentAccountList;
    }

    public List<CardTransaction> getCardTransactionOr() {
        return cardTransactionOr;
    }

    public void setCardTransactionOr(List<CardTransaction> cardTransactionOr) {
        this.cardTransactionOr = cardTransactionOr;
    }

    public List<CardTransaction> getCardTransactionBf() {
        return cardTransactionBf;
    }

    public void setCardTransactionBf(List<CardTransaction> cardTransactionBf) {
        this.cardTransactionBf = cardTransactionBf;
    }

    public List<IbanTransaction> getIbanTransactionsOr() {
        return ibanTransactionsOr;
    }

    public void setIbanTransactionsOr(List<IbanTransaction> ibanTransactionsOr) {
        this.ibanTransactionsOr = ibanTransactionsOr;
    }

    public List<IbanTransaction> getIbanTransactionsBf() {
        return ibanTransactionsBf;
    }

    public void setIbanTransactionsBf(List<IbanTransaction> ibanTransactionsBf) {
        this.ibanTransactionsBf = ibanTransactionsBf;
    }

    public List<DetailsCustomerServiceBank> getDetailsCustomerServiceBankList() {
        return detailsCustomerServiceBankList;
    }

    public void setDetailsCustomerServiceBankList(List<DetailsCustomerServiceBank> detailsCustomerServiceBankList) {
        this.detailsCustomerServiceBankList = detailsCustomerServiceBankList;
    }
}
