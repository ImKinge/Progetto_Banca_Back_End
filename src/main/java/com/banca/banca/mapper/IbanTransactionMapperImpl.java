package com.banca.banca.mapper;

import com.banca.banca.dto.IbanTransactionDto;
import com.banca.banca.entity.IbanTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Component
public class IbanTransactionMapperImpl implements IbanTransactionMapper {

    @Autowired
    CustomerDataMapper customerDataMapper;

    @Override
    public IbanTransactionDto toIbanTransactionDto(IbanTransaction ibanTransaction) {

        if ( ibanTransaction == null ) {
            return null;
        }

//        LocalDateTime now = ibanTransaction.getDateTransaction();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//        String formatDateTime = now.format(formatter);
//        LocalDateTime localDateTime = LocalDateTime.parse(formatDateTime, formatter);

        double value = ibanTransaction.getAmount();
        String decimalFormat  = new DecimalFormat("#.00").format(value);//per inserire 2 cifre decimali dopo la virgola se il double fosse una cifra intera
        IbanTransactionDto ibanTransactionDto = new IbanTransactionDto();

        ibanTransactionDto.setOrderer(customerDataMapper.toCustomerDataDto(ibanTransaction.getCustomerDataOr()));
        ibanTransactionDto.setAmount(decimalFormat);
        ibanTransactionDto.setDateTransaction(ibanTransaction.getDateTransaction());
        ibanTransactionDto.setDescriptionTransaction(ibanTransaction.getDescriptionTransaction());
        ibanTransactionDto.setBeneficiary(customerDataMapper.toCustomerDataDto(ibanTransaction.getCustomerDataBf()));

        return ibanTransactionDto;
    }

    @Override
    public IbanTransaction toIbanTransaction(IbanTransactionDto ibanTransactionDto) {

        IbanTransaction ibanTransaction = new IbanTransaction();

        ibanTransaction.setAmount(Double.parseDouble(ibanTransactionDto.getAmount()));
        ibanTransaction.setDescriptionTransaction(ibanTransactionDto.getDescriptionTransaction());
        ibanTransaction.setCustomerDataOr(customerDataMapper.toCustomerData(ibanTransactionDto.getOrderer()));
        ibanTransaction.setCustomerDataBf(customerDataMapper.toCustomerData(ibanTransactionDto.getBeneficiary()));

        return ibanTransaction;
    }

    public List<IbanTransactionDto> toListIbanTransactionDto (List<IbanTransaction> ibanTransactionList) {

        if(ibanTransactionList == null) {
            return null;
        }

        List<IbanTransactionDto> ibanTransactionDtoList = new ArrayList<>(ibanTransactionList.size());

        for ( IbanTransaction ibanTransaction: ibanTransactionList) {
            ibanTransactionDtoList.add(toIbanTransactionDto(ibanTransaction));
        }

        return ibanTransactionDtoList;
    }

}
