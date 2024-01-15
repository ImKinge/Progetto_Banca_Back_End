package com.banca.banca.mapper;

import com.banca.banca.dto.DetailsCustomerServiceBankDto;
import com.banca.banca.entity.DetailsCustomerServiceBank;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DetailsCustomerServiceBankMapperImpl implements DetailsCustomerServiceBankMapper{
    @Override
    public DetailsCustomerServiceBankDto toDetailsCustomerServiceBankDto(DetailsCustomerServiceBank detailsCustomerServiceBank) {

        DetailsCustomerServiceBankDto detailsCustomerServiceBankDto = new DetailsCustomerServiceBankDto();

        detailsCustomerServiceBankDto.setId(detailsCustomerServiceBank.getId());
        detailsCustomerServiceBankDto.setServiceBank(detailsCustomerServiceBank.getServiceBank());
        detailsCustomerServiceBankDto.setPurchaseDate(detailsCustomerServiceBank.getPurchaseDate());

        return detailsCustomerServiceBankDto;
    }

    @Override
    public DetailsCustomerServiceBank toDetailsCustomerServiceBank(DetailsCustomerServiceBankDto detailsCustomerServiceBankDto) {

        DetailsCustomerServiceBank detailsCustomerServiceBank = new DetailsCustomerServiceBank();

        detailsCustomerServiceBank.setId(detailsCustomerServiceBankDto.getId());
        detailsCustomerServiceBank.setServiceBank(detailsCustomerServiceBankDto.getServiceBank());
        detailsCustomerServiceBank.setPurchaseDate(detailsCustomerServiceBankDto.getPurchaseDate());

        return detailsCustomerServiceBank;
    }

    @Override
    public List<DetailsCustomerServiceBankDto> toDetailsCustomerServiceBankDtoList(List<DetailsCustomerServiceBank> detailsCustomerServiceBankList) {

        List<DetailsCustomerServiceBankDto> detailsCustomerServiceBankDtoList = new ArrayList<>();

        for(DetailsCustomerServiceBank detailsCustomerServiceBank : detailsCustomerServiceBankList) {
            detailsCustomerServiceBankDtoList.add(toDetailsCustomerServiceBankDto(detailsCustomerServiceBank));
        }

        return detailsCustomerServiceBankDtoList;
    }
}
