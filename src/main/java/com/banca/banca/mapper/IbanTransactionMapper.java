package com.banca.banca.mapper;


import com.banca.banca.dto.IbanTransactionDto;
import com.banca.banca.entity.IbanTransaction;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface IbanTransactionMapper {

    public IbanTransactionDto toIbanTransactionDto (IbanTransaction ibanTransaction);

    IbanTransaction toIbanTransaction(IbanTransactionDto ibanTransactionDto);

    List<IbanTransactionDto> toListIbanTransactionDto (List<IbanTransaction> ibanTransactionList);
}
