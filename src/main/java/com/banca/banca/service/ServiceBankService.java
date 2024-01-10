package com.banca.banca.service;

import com.banca.banca.dto.ServiceBankDto;
import com.banca.banca.entity.ServiceBank;

import java.util.List;

public interface ServiceBankService {

    ServiceBank purchaseService();

    List<ServiceBankDto> findAllServiceProduct ();
}
