package com.banca.banca.controller;

import com.banca.banca.dto.CurrentAccountDto;
import com.banca.banca.dto.CustomerDataDto;
import com.banca.banca.dto.ResponseDto;
import com.banca.banca.exception.CurrentAccountException;
import com.banca.banca.exception.CustomerDataException;
import com.banca.banca.service.CurrentAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/current-account")
@CrossOrigin(origins = "http://localhost:4200" , allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class CurrentAccountController {

    @Autowired
    private CurrentAccountService currentAccountService;


    @GetMapping("/customer")
    public ResponseEntity<ResponseDto<CurrentAccountDto>> findCurrentAccountByFiscalCode(@RequestHeader (HttpHeaders.AUTHORIZATION) String token) throws CurrentAccountException {

        try {
            CurrentAccountDto currentAccountDto = currentAccountService.findByFiscalCode(token);
            return new ResponseEntity<>(new ResponseDto<>(currentAccountDto, HttpStatus.OK, true), HttpStatus.OK);
        } catch (CurrentAccountException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list-iban-number/{ibanNumber}")
    public List<CurrentAccountDto> findListByIbanNumber (@PathVariable String ibanNumber) {
        return currentAccountService.findListByIbanNumber(ibanNumber);
    }

}
