package com.banca.banca.dto;

import com.banca.banca.entity.Role;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class CustomerDataDto implements Serializable {

    private String fiscalCode;

    private String name;

    private String surname;

    private LocalDate dateOfBirth;

    private String email;

    private Long infoPhone;

    private String address;

    private String username;

    private String password;

    private List<RoleDto> roles;

    private List<CurrentAccountDto> currentAccountDto;


    //Constructor
    public CustomerDataDto() {}

    public CustomerDataDto(String fiscalCode, String name, String surname, LocalDate dateOfBirth, String email, Long infoPhone, String address, String username, String password, List<RoleDto> roles, List<CurrentAccountDto> currentAccountDto) {
        this.fiscalCode = fiscalCode;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.infoPhone = infoPhone;
        this.address = address;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.currentAccountDto = currentAccountDto;
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


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public List<RoleDto> getRoles() { return roles; }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }


    public List<CurrentAccountDto> getCurrentAccountDto() {
        return currentAccountDto;
    }

    public void setCurrentAccountDto(List<CurrentAccountDto> currentAccountDto) {
        this.currentAccountDto = currentAccountDto;
    }
}
