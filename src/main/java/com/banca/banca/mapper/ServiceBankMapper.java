package com.banca.banca.mapper;

import com.banca.banca.dto.ServiceBankDto;
import com.banca.banca.entity.ServiceBank;

import java.util.List;

public interface ServiceBankMapper {

    ServiceBankDto toServiceBankDto (ServiceBank serviceBank);

    ServiceBank toServiceBank (ServiceBankDto serviceBankDto);

    List<ServiceBankDto> toListServiceBankDto (List<ServiceBank> serviceBankList);
}
