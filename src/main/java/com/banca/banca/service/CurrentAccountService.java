package com.banca.banca.service;


import com.banca.banca.dto.CurrentAccountDto;
import com.banca.banca.exception.CurrentAccountException;

import java.util.List;

public interface CurrentAccountService {

    CurrentAccountDto findByIbanNumber (String ibanNumber) throws CurrentAccountException;

    CurrentAccountDto findByFiscalCode (String fiscalCode) throws CurrentAccountException;

    List<CurrentAccountDto> findListByIbanNumber(String ibanNumber);

}
