package com.banca.banca.controller;

import com.banca.banca.dto.CardTransactionDto;
import com.banca.banca.dto.IbanTransactionDto;
import com.banca.banca.dto.ResponseDto;
import com.banca.banca.security.jwt.JWTGenerator;
import com.banca.banca.service.CardTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins = "http://localhost:4200" , allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class CardTransactionController {


    @Autowired
    private CardTransactionService cardTransactionService;

    @Autowired
    private JWTGenerator jwtGenerator;


    /**
     *Chiamata per ritornarmi una lista di transazioni carta tramite un fiscalCode che recuperiamo dall'header della chiamata
     *
     * Una volta fatto il login ci verrà restituito un token che noi tramite @RequestHeader andiamo a recuperare e valutare
     * Tramite il metodo della classe JwtGenerator = getFiscalCodeFromJWT() andiamo a recuperare il fiscalCode e lo usiamo per farci restituire
     * la lista delle transazioni di un utente sia che è beneficiario sia che è ordinario
     *
     * @param token
     * parametro sia per l'ordinario che il beneficiario delle transazioni
     *
     * @return
     * Chiamata per ritornare una lista di transazioni delle carte filtrate
     * per codice fiscale dell'ordinario e del beneficiario
     *
     */
    @GetMapping("/card-transaction")
    public ResponseEntity<ResponseDto<List<CardTransactionDto>>> getCardTransaction (@RequestHeader (HttpHeaders.AUTHORIZATION) String token) {

        String fiscalCode = jwtGenerator.getFiscalCodeFromJWT(token);

        List<CardTransactionDto> cardTransactionDtoList = cardTransactionService.getCardTransaction(fiscalCode);

        HttpStatus httpStatus = HttpStatus.OK;

        if (cardTransactionDtoList.isEmpty()) {
            httpStatus = HttpStatus.NO_CONTENT;
        }

        return new ResponseEntity<>(new ResponseDto<>(cardTransactionDtoList, httpStatus, true), HttpStatus.OK);
    }


}
