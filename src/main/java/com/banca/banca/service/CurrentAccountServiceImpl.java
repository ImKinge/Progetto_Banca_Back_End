package com.banca.banca.service;

import com.banca.banca.dto.CurrentAccountDto;
import com.banca.banca.entity.CurrentAccount;
import com.banca.banca.exception.CurrentAccountException;
import com.banca.banca.mapper.CurrentAccountMapper;
import com.banca.banca.repository.CurrentAccountRepository;
import com.banca.banca.security.jwt.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CurrentAccountServiceImpl implements CurrentAccountService{


    @Autowired
    private CurrentAccountRepository currentAccountRepository;

    @Autowired
    private CurrentAccountMapper currentAccountMapper;

    @Autowired
    private JWTGenerator jwtGenerator;


    @Override
    public CurrentAccountDto findByIbanNumber(String ibanNumber) throws CurrentAccountException {
        Optional<CurrentAccount> currentAccount = currentAccountRepository.findByIbanNumber(ibanNumber);
        CurrentAccountDto currentAccountDto = currentAccountMapper.toCurrentAccountDto(currentAccount.orElseThrow(() -> new CurrentAccountException("Nessun conto corrente trovato con iban: " + ibanNumber)));

        return currentAccountDto;
    }

    @Override
    public CurrentAccountDto findByFiscalCode(String token) throws CurrentAccountException {
        String fiscalCode = jwtGenerator.getFiscalCodeFromJWT(token);
        CurrentAccount currentAccount = currentAccountRepository.findByFiscalCode(fiscalCode).orElseThrow(() -> new CurrentAccountException("Nessun conto corrente trovato con fiscalCode: " + fiscalCode));

        return currentAccountMapper.toCurrentAccountDto(currentAccount);
    }

    @Override
    public List<CurrentAccountDto> findListByIbanNumber(String ibanNumber) {
        List<CurrentAccount> currentAccountList = currentAccountRepository.findAllByIbanNumber(ibanNumber);

        return currentAccountMapper.toListCurrentAccountDto(currentAccountList);
    }
}
