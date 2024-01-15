package com.banca.banca.service;

import com.banca.banca.dto.CartDto;
import com.banca.banca.entity.Cart;
import com.banca.banca.exception.CustomerDataException;
import com.banca.banca.exception.ServiceBankException;

import java.util.List;

public interface CartService {

    Cart addToCart(Integer productId, String token) throws CustomerDataException, ServiceBankException;

    void removeToCart(Integer productId) throws ServiceBankException, CustomerDataException;

    List<CartDto> findAllProductsByUser (String fiscalCode);
}
