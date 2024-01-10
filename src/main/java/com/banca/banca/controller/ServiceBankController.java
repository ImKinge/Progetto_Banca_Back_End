package com.banca.banca.controller;

import com.banca.banca.dto.ServiceBankDto;
import com.banca.banca.entity.ServiceBank;
import com.banca.banca.security.jwt.JWTGenerator;
import com.banca.banca.service.ServiceBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/service-bank")
@CrossOrigin(origins = "http://localhost:4200" , allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class ServiceBankController {

    @Autowired
    private ServiceBankService serviceBankService;

    @GetMapping("/get-all-product")
    public ResponseEntity<List<ServiceBankDto>> findAllServiceProduct () {
        return new ResponseEntity<>(serviceBankService.findAllServiceProduct(), HttpStatus.OK);
    }
}
