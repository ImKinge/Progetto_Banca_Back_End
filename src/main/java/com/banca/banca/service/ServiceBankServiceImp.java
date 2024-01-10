package com.banca.banca.service;

import com.banca.banca.dto.ServiceBankDto;
import com.banca.banca.entity.ServiceBank;
import com.banca.banca.mapper.ServiceBankMapper;
import com.banca.banca.repository.ServiceBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceBankServiceImp implements ServiceBankService {

    @Autowired
    private ServiceBankRepository serviceBankRepository;

    @Autowired
    private ServiceBankMapper serviceBankMapper;

    @Override
    public ServiceBank purchaseService() {
        return null;
    }

    @Override
    public List<ServiceBankDto> findAllServiceProduct() {
        return serviceBankMapper.toListServiceBankDto(serviceBankRepository.findAll());
    }
}
