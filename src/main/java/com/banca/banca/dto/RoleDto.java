package com.banca.banca.dto;

import java.util.List;

public class RoleDto {

    private String name;

    private List<CustomerDataDto> customerDataDtoList;


    //Constructor
    public RoleDto() {
    }

    public RoleDto(String name, List<CustomerDataDto> customerDataDtoList) {
        this.name = name;
        this.customerDataDtoList = customerDataDtoList;
    }


    //Getter and Setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CustomerDataDto> getCustomerDataDtoList() {
        return customerDataDtoList;
    }

    public void setCustomerDataDtoList(List<CustomerDataDto> customerDataDtoList) {
        this.customerDataDtoList = customerDataDtoList;
    }
}
