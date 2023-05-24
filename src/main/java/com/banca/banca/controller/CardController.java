package com.banca.banca.controller;

import com.banca.banca.dto.CardDto;
import com.banca.banca.dto.ResponseDto;
import com.banca.banca.exception.CardException;
import com.banca.banca.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/card")
@CrossOrigin(origins = "http://localhost:4200" , allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class CardController {

    @Autowired
    private CardService cardService;


    @GetMapping("/card-number")
    public ResponseEntity<ResponseDto<CardDto>> findCardByFiscalCode(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) throws CardException {
        try {
            CardDto cardDto = cardService.findByFiscalCode(token);
            return new ResponseEntity<>(new ResponseDto<>(cardDto, HttpStatus.OK, true), HttpStatus.OK);
        } catch (CardException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
