package com.banca.banca.service;

import com.banca.banca.dto.CartDto;
import com.banca.banca.entity.Cart;
import com.banca.banca.entity.CustomerData;
import com.banca.banca.entity.ServiceBank;
import com.banca.banca.exception.CustomerDataException;
import com.banca.banca.exception.ServiceBankException;
import com.banca.banca.mapper.CartMapper;
import com.banca.banca.repository.CartRepository;
import com.banca.banca.repository.CustomerDataRepository;
import com.banca.banca.repository.ServiceBankRepository;
import com.banca.banca.security.jwt.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private JWTGenerator jwtGenerator;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ServiceBankRepository serviceBankRepository;

    @Autowired
    private CustomerDataRepository customerDataRepository;


    @Override
    public Cart addToCart(Integer productId, String fiscalCode) throws ServiceBankException, CustomerDataException {

        ServiceBank serviceBank = serviceBankRepository.findById(productId).orElseThrow(() -> new ServiceBankException("Service Bank not find!"));
        CustomerData customerData = customerDataRepository.findByFiscalCode(fiscalCode).orElseThrow(() -> new CustomerDataException("Customer not find!"));

        Cart cart = new Cart(serviceBank, customerData);

        return cartRepository.save(cart);
    }

    @Override
    public void removeToCart(Integer cartItemId) {
        cartRepository.deleteById(cartItemId);

    }

    @Override
    public List<CartDto> findAllProductsByUser(String fiscalCode) {

        List<Cart> cart = cartRepository.findAllById(fiscalCode);

        return cartMapper.toCartDtoList(cart);
    }
}
