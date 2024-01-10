package com.banca.banca.mapper;

import com.banca.banca.dto.ServiceBankDto;
import com.banca.banca.entity.ServiceBank;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceBankMapperImpl implements ServiceBankMapper{

    @Override
    public ServiceBankDto toServiceBankDto(ServiceBank serviceBank) {

        if(serviceBank == null) {
            return null;
        }

        ServiceBankDto serviceBankDto = new ServiceBankDto();

        serviceBankDto.setId(serviceBank.getId());
        serviceBankDto.setCategory(serviceBank.getCategory());
        serviceBankDto.setServiceName(serviceBank.getServiceName());
        serviceBankDto.setMonthlyFee(serviceBank.getMonthlyFee());
        serviceBankDto.setDescription(serviceBank.getDescription());

        return serviceBankDto;
    }

    @Override
    public ServiceBank toServiceBank(ServiceBankDto serviceBankDto) {

        if(serviceBankDto == null) {
            return null;
        }

        ServiceBank serviceBank = new ServiceBank();

        serviceBank.setId(serviceBankDto.getId());
        serviceBank.setCategory(serviceBankDto.getCategory());
        serviceBank.setServiceName(serviceBankDto.getServiceName());
        serviceBank.setMonthlyFee(serviceBankDto.getMonthlyFee());
        serviceBank.setDescription(serviceBankDto.getDescription());

        return serviceBank;
    }

    @Override
    public List<ServiceBankDto> toListServiceBankDto(List<ServiceBank> serviceBankList) {

        if(serviceBankList == null) {
            return null;
        }

        List<ServiceBankDto> serviceBankDtoList = new ArrayList<>();

        for(ServiceBank serviceBank : serviceBankList) {
            serviceBankDtoList.add(toServiceBankDto(serviceBank));
        }

        return serviceBankDtoList;
    }
}
