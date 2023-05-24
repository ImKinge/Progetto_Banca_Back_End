package com.banca.banca.mapper;


import com.banca.banca.dto.CustomerDataDto;
import com.banca.banca.entity.CustomerData;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CustomerDataMapperImpl implements CustomerDataMapper {


    private final PasswordEncoder passwordEncoder;

    public CustomerDataMapperImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CustomerDataDto toCustomerDataDto(CustomerData customerData) {

        if(customerData == null) {
            return null;
        }

        CustomerDataDto customerDataDto = new CustomerDataDto();

        customerDataDto.setFiscalCode(customerData.getFiscalCode().toUpperCase());
        customerDataDto.setName(customerData.getName());
        customerDataDto.setSurname(customerData.getSurname());
        customerDataDto.setDateOfBirth(customerData.getDateOfBirth());
        customerDataDto.setEmail(customerData.getEmail());
        customerDataDto.setAddress(customerData.getAddress());
        customerDataDto.setInfoPhone(customerData.getInfoPhone());

        return customerDataDto;
    }

    @Override
    public CustomerData toCustomerData(CustomerDataDto customerDataDto) {

        CustomerData customerData = new CustomerData();

        customerData.setFiscalCode(customerDataDto.getFiscalCode().toUpperCase());
        customerData.setName(customerDataDto.getName());
        customerData.setSurname(customerDataDto.getSurname());
        customerData.setDateOfBirth(customerDataDto.getDateOfBirth());
        customerData.setEmail(customerDataDto.getEmail());
        customerData.setInfoPhone(customerDataDto.getInfoPhone());
        customerData.setAddress(customerDataDto.getAddress());
        customerData.setUserName(customerDataDto.getUsername());
        customerData.setPassword(passwordEncoder.encode((customerDataDto.getPassword())));

        return customerData;
    }

    @Override
    public List<CustomerData> toListCustomerData(List<CustomerDataDto> customerDataDtoList) {

        if (customerDataDtoList == null) {
            return null;
        }

        List<CustomerData> customerDataList = new ArrayList<>(customerDataDtoList.size());

        for (CustomerDataDto customerDataDto : customerDataDtoList) {
            customerDataList.add(toCustomerData(customerDataDto));
        }

        return customerDataList;
    }

    @Override
    public List<CustomerDataDto> toListCustomerDataDto(List<CustomerData> customerDataList) {
        if (customerDataList == null) {
            return null;
        }

        List<CustomerDataDto> customerDataDtoList = new ArrayList<>(customerDataList.size());

        for (CustomerData customerData : customerDataList) {
            customerDataDtoList.add(toCustomerDataDto(customerData));
        }

        return customerDataDtoList;
    }
}
