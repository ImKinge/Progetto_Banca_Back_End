package com.banca.banca.service;

import com.banca.banca.dto.IbanTransactionDto;
import com.banca.banca.dto.TransferDto;
import com.banca.banca.entity.IbanTransaction;
import com.banca.banca.exception.CurrentAccountException;
import com.banca.banca.exception.CustomerDataException;
import com.banca.banca.exception.TransferException;

import java.util.List;
import java.util.Optional;

public interface IbanTransactionService {

    Optional<IbanTransaction> findIbanById(Integer id);

    List<IbanTransactionDto> getIbanTransaction (String fiscalCode);

    IbanTransaction makeTransfers(TransferDto transferDto, String token) throws TransferException, CustomerDataException, CurrentAccountException;
}
