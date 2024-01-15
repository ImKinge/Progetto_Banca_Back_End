package com.banca.banca.service;

import com.banca.banca.dto.DetailsCustomerServiceBankDto;
import com.banca.banca.entity.DetailsCustomerServiceBank;
import com.banca.banca.mapper.DetailsCustomerServiceBankMapper;
import com.banca.banca.repository.CartRepository;
import com.banca.banca.repository.DetailsCustomerServiceBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DetailsCustomerServiceBankServiceImpl implements DetailsCustomerServiceBankService {

    @Autowired
    private DetailsCustomerServiceBankMapper detailsCustomerServiceBankMapper;

    @Autowired
    private DetailsCustomerServiceBankRepository detailsCustomerServiceBankRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    @Transactional
    public void purchaseServiceBank(List<DetailsCustomerServiceBank> detailsCustomerServiceBankList, String fiscalCode) {

        LocalDateTime purchaseDate = LocalDateTime.now();
        for(DetailsCustomerServiceBank detailsCustomerServiceBank : detailsCustomerServiceBankList) {
            detailsCustomerServiceBank.setPurchaseDate(purchaseDate);
        }
        detailsCustomerServiceBankRepository.saveAll(detailsCustomerServiceBankList);
        if(!detailsCustomerServiceBankList.isEmpty()) {
            cartRepository.deleteAllByCustomerData(fiscalCode);
        }

    }

    @Override
    public List<DetailsCustomerServiceBankDto> getActivateServicesByCustomer(String fiscalCode) {
        List<DetailsCustomerServiceBank> detailsCustomerServiceBanks = detailsCustomerServiceBankRepository.findAllByFiscalCode(fiscalCode);

        return detailsCustomerServiceBankMapper.toDetailsCustomerServiceBankDtoList(detailsCustomerServiceBanks);
    }
}
