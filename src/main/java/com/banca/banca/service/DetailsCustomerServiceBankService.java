package com.banca.banca.service;

import com.banca.banca.dto.DetailsCustomerServiceBankDto;
import com.banca.banca.entity.DetailsCustomerServiceBank;

import java.util.List;

public interface DetailsCustomerServiceBankService {
    void purchaseServiceBank(List<DetailsCustomerServiceBank> detailsCustomerServiceBankList, String fiscalCode);

    List<DetailsCustomerServiceBankDto> getActivateServicesByCustomer(String fiscalCode);
}
