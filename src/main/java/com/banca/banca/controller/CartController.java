package com.banca.banca.controller;

import com.banca.banca.dto.CartDto;
import com.banca.banca.dto.ResponseDto;
import com.banca.banca.entity.Cart;
import com.banca.banca.exception.CartException;
import com.banca.banca.exception.CustomerDataException;
import com.banca.banca.exception.ServiceBankException;
import com.banca.banca.exception.TransferException;
import com.banca.banca.mapper.CartMapper;
import com.banca.banca.security.jwt.JWTGenerator;
import com.banca.banca.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:4200" , allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private JWTGenerator jwtGenerator;

    @Autowired
    private CartMapper cartMapper;

    @GetMapping("/add-to-cart/{productId}")
    public ResponseEntity<ResponseDto<List<CartDto>>> addToCart (@PathVariable(name = "productId") Integer productId, @RequestHeader (HttpHeaders.AUTHORIZATION) String token) throws CustomerDataException, ServiceBankException {

            String fiscalCode = jwtGenerator.getFiscalCodeFromJWT(token);

            cartMapper.toCartDto(cartService.addToCart(productId, fiscalCode));
            List<CartDto> cartDtoList = cartService.findAllProductsByUser(fiscalCode);

            return new ResponseEntity<>(new ResponseDto<>(cartDtoList, HttpStatus.OK, true), HttpStatus.OK);
    }

    @DeleteMapping("/delete-to-cart/{cartItemId}")
    public ResponseEntity<ResponseDto<List<CartDto>>> removeToCart (@PathVariable(name = "cartItemId") Integer cartItemId, @RequestHeader (HttpHeaders.AUTHORIZATION) String token) throws CustomerDataException, ServiceBankException {

        String fiscalCode = jwtGenerator.getFiscalCodeFromJWT(token);
        cartService.removeToCart(cartItemId);
        List<CartDto> cartDtoList = cartService.findAllProductsByUser(fiscalCode);

        return new ResponseEntity<>(new ResponseDto<>(cartDtoList, HttpStatus.OK, true), HttpStatus.OK);

    }

    @GetMapping(value = "/get-cart-details", produces = "application/json")
    public ResponseEntity<ResponseDto<List<CartDto>>> getCartDetails (@RequestHeader (HttpHeaders.AUTHORIZATION) String token) {

        String fiscalCode = jwtGenerator.getFiscalCodeFromJWT(token);

        List<CartDto> cartDtoList = cartService.findAllProductsByUser(fiscalCode);

        HttpStatus httpStatus = HttpStatus.OK;

        if (cartDtoList.isEmpty()) {
            httpStatus = HttpStatus.NO_CONTENT;
        }

        return new ResponseEntity<>(new ResponseDto<>(cartDtoList, httpStatus, true), HttpStatus.OK);
    }
}
