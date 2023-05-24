package com.banca.banca.mapper;

import com.banca.banca.dto.CurrentAccountDto;
import com.banca.banca.entity.CurrentAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CurrentAccountMapperImp implements CurrentAccountMapper{

    @Autowired
    CustomerDataMapper customerDataMapper;


    @Override
    public CurrentAccountDto toCurrentAccountDto(CurrentAccount currentAccount) {

        if(currentAccount == null) {
            return null;
        }

        CurrentAccountDto currentAccountDto = new CurrentAccountDto();

        currentAccountDto.setIbanNumber(currentAccount.getIbanNumber());
        currentAccountDto.setBalance(currentAccount.getBalance());
        currentAccountDto.setFiscalCode(customerDataMapper.toCustomerDataDto(currentAccount.getCustomerData()));

        return currentAccountDto;
    }

    @Override
    public CurrentAccount toCurrentAccount(CurrentAccountDto currentAccountDto) {

        CurrentAccount currentAccount = new CurrentAccount();

        currentAccount.setIbanNumber(currentAccountDto.getIbanNumber());
        currentAccount.setBalance(currentAccountDto.getBalance());
        currentAccount.setCustomerData(customerDataMapper.toCustomerData(currentAccountDto.getFiscalCode()));

        return currentAccount;
    }

    @Override
    public List<CurrentAccount> toListCurrentAccount(List<CurrentAccountDto> currentAccountDtoList) {

        if (currentAccountDtoList.isEmpty()) {
            return null;
        }

        List<CurrentAccount> currentAccountList = new ArrayList<>(currentAccountDtoList.size());

        for (CurrentAccountDto currentAccountDto : currentAccountDtoList) {
            currentAccountList.add(toCurrentAccount(currentAccountDto));
        }

        return currentAccountList;
    }

    @Override
    public List<CurrentAccountDto> toListCurrentAccountDto(List<CurrentAccount> currentAccountList) {

        if (currentAccountList.isEmpty()) {
            return null;
        }

        List<CurrentAccountDto> currentAccountDtoList = new ArrayList<>(currentAccountList.size());

        for (CurrentAccount currentAccount : currentAccountList) {
            currentAccountDtoList.add(toCurrentAccountDto(currentAccount));
        }

        return currentAccountDtoList;
    }
}
