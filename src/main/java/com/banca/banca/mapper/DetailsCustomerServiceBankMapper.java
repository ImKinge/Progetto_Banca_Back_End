package com.banca.banca.mapper;

import com.banca.banca.dto.DetailsCustomerServiceBankDto;
import com.banca.banca.entity.DetailsCustomerServiceBank;

import java.util.List;

public interface DetailsCustomerServiceBankMapper {

    DetailsCustomerServiceBankDto toDetailsCustomerServiceBankDto (DetailsCustomerServiceBank detailsCustomerServiceBank);

    DetailsCustomerServiceBank toDetailsCustomerServiceBank (DetailsCustomerServiceBankDto detailsCustomerServiceBankDto);

    List<DetailsCustomerServiceBankDto> toDetailsCustomerServiceBankDtoList( List<DetailsCustomerServiceBank> detailsCustomerServiceBankList);
}
