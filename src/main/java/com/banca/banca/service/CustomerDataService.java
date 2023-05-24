package com.banca.banca.service;

import com.banca.banca.dto.CustomerDataDto;
import com.banca.banca.entity.CustomerData;
import com.banca.banca.exception.CustomerDataException;
import com.banca.banca.exception.RoleException;


public interface CustomerDataService {

    CustomerDataDto findByFiscalCode (String fiscalCode) throws CustomerDataException;

    CustomerDataDto findByUsername (String userName) throws CustomerDataException;

    CustomerDataDto findByUsernameAndPassword (String userName, String password) throws CustomerDataException;

    CustomerData saveCustomer(CustomerDataDto customerDataDto) throws RoleException;

}
