package com.banca.banca.controller;

import com.banca.banca.dto.DetailsCustomerServiceBankDto;
import com.banca.banca.dto.ResponseDto;
import com.banca.banca.entity.DetailsCustomerServiceBank;
import com.banca.banca.security.jwt.JWTGenerator;
import com.banca.banca.service.DetailsCustomerServiceBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/details")
@CrossOrigin(origins = "http://localhost:4200" , allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class DetailsCustomerServiceBankController {

    @Autowired
    private DetailsCustomerServiceBankService detailsCustomerServiceBankService;

    @Autowired
    private JWTGenerator jwtGenerator;

    @PostMapping("/purchase-service-bank")
    public ResponseEntity<ResponseDto<String>> purchaseServiceBank (@RequestBody List<DetailsCustomerServiceBank> detailsCustomerServiceBankList,
                                                                    @RequestHeader (HttpHeaders.AUTHORIZATION) String token) {

        String fiscalCode = jwtGenerator.getFiscalCodeFromJWT(token);

        detailsCustomerServiceBankService.purchaseServiceBank(detailsCustomerServiceBankList, fiscalCode);

        return new ResponseEntity<>(new ResponseDto<>("Acquisto avvenuto con successo!", HttpStatus.OK, true), HttpStatus.OK);
    }

    @GetMapping("/get-activate-services")
    public ResponseEntity<ResponseDto<List<DetailsCustomerServiceBankDto>>> getActivateServicesByCustomer (@RequestHeader (HttpHeaders.AUTHORIZATION) String token) {

        String fiscalCode = jwtGenerator.getFiscalCodeFromJWT(token);

        List<DetailsCustomerServiceBankDto> detailsCustomerServiceBankDtos = detailsCustomerServiceBankService.getActivateServicesByCustomer(fiscalCode);

        return new ResponseEntity<>(new ResponseDto<>(detailsCustomerServiceBankDtos, HttpStatus.OK, true), HttpStatus.OK);

    }
}
