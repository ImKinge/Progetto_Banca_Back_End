package com.banca.banca.service;

import com.banca.banca.dto.CustomerDataDto;
import com.banca.banca.entity.CurrentAccount;
import com.banca.banca.entity.CustomerData;
import com.banca.banca.entity.Role;
import com.banca.banca.exception.CustomerDataException;
import com.banca.banca.exception.RoleException;
import com.banca.banca.helper.BankHelper;
import com.banca.banca.mapper.CustomerDataMapper;
import com.banca.banca.repository.CurrentAccountRepository;
import com.banca.banca.repository.CustomerDataRepository;
import com.banca.banca.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;



@Service
public class CustomerDataServiceImpl implements CustomerDataService {

    private final CustomerDataRepository customerDataRepository;
    private final CustomerDataMapper customerDataMapper;
    private final RoleRepository roleRepository;
    private final CurrentAccountRepository currentAccountRepository;
    private final BankHelper bankHelper;

    @Autowired
    public CustomerDataServiceImpl(CustomerDataRepository customerDataRepository, CustomerDataMapper customerDataMapper, RoleRepository roleRepository, CurrentAccountRepository currentAccountRepository, BankHelper bankHelper) {
        this.customerDataRepository = customerDataRepository;
        this.customerDataMapper = customerDataMapper;
        this.roleRepository = roleRepository;
        this.currentAccountRepository = currentAccountRepository;
        this.bankHelper = bankHelper;
    }

    @Override
    public CustomerDataDto findByFiscalCode(String fiscalCode) throws CustomerDataException {
        Optional<CustomerData> customerData = customerDataRepository.findByFiscalCode(fiscalCode);

        return customerDataMapper.toCustomerDataDto(customerData.orElseThrow(() -> new CustomerDataException("Nessun utente trovato con fiscalCode: " + fiscalCode)));
    }

    @Override
    public CustomerDataDto findByUsername(String userName) throws CustomerDataException {
        Optional<CustomerData> customerData = customerDataRepository.findByUserName(userName);
        return customerDataMapper.toCustomerDataDto(customerData.orElseThrow(() -> new CustomerDataException("Nessun utente trovato con Username: " + userName)));
    }

    @Override
    public CustomerDataDto findByUsernameAndPassword(String userName, String password) throws CustomerDataException {

        Optional<CustomerData> customerData = customerDataRepository.findByUserNameAndPassword(userName,password);
        return customerDataMapper.toCustomerDataDto(customerData.orElseThrow(() -> new CustomerDataException("Nessun utente trovato con Username: " + userName + ", e Password: " + password)));
    }

    /**
     * Permette di registrare un utente generando anche un iban intestato alla persona che si registra, usando il metodo generateIban()
     * della classe BankHelper
     * Per vedere salvati i dati su db sulla tabella in comune tra customerData e role bisogna usare l'ENTITY E NON IL DTO
     *
     * @param customerDataDto request
     * @return CustomerData
     * @throws RoleException eccezzione
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomerData saveCustomer(CustomerDataDto customerDataDto) throws RoleException {
        CustomerData customerData = customerDataMapper.toCustomerData(customerDataDto);

        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setIbanNumber(bankHelper.generateIban());
        currentAccount.setCustomerData(customerData);
        currentAccountRepository.save(currentAccount);

        Role role = roleRepository.findByName("USER").orElseThrow(() -> new RoleException("Role not found!"));
        customerData.setRoles(Collections.singletonList(role)); //singletonList() viene utilizzato per restituire un elenco immutabile contenente solo l'oggetto specificato
        customerData.setCurrentAccountList(Collections.singletonList(currentAccount));
        return customerDataRepository.save(customerData);
    }

}
