package com.banca.banca.controller;

import com.banca.banca.dto.IbanTransactionDto;
import com.banca.banca.dto.ResponseDto;
import com.banca.banca.dto.TransferDto;
import com.banca.banca.exception.TransferException;
import com.banca.banca.security.jwt.JWTGenerator;
import com.banca.banca.service.IbanTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller per recuperare la lista delle transizioni dell'iban per un determinato ordinario e beneficiario
 * e azione per effettuare un bonifico
 */
@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins = "http://localhost:4200" , allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class IbanTransactionController {


    @Autowired
    private IbanTransactionService ibanTransactionService;

    @Autowired
    private JWTGenerator jwtGenerator;

    /**
     * Chiamata per ritornarmi una lista di transazioni iban tramite un fiscalCode che recuperiamo dall'header della chiamata
     *
     * Una volta fatto il login ci verrà restituito un token che noi tramite @RequestHeader andiamo a recuperare e valutare
     *
     * Tramite il metodo della classe JwtGenerator = getFiscalCodeFromJWT() andiamo a recuperare il fiscalCode e lo usiamo per farci restituire
     * la lista delle transazioni di un utente sia che è beneficiario sia che è ordinario
     *
     * @param token parametro sia per l'ordinario che il beneficiario delle transazioni
     * @return Chiamata per ritornare una lista di transazioni dell'iban filtrate
     * per codice fiscale dell'ordinario e del beneficiario.
     * Se è vuota tornerò una ResponseEntity con lo statusCode NO.CONTENT
     * Altrimenti con lo statusCode OK
     */
    @GetMapping("/iban-transaction")
    //Il generic va inserito dentro le parentesi angolari del ResponsDto<>, in questo caso è una lista di ibanTransactionDto
    public ResponseEntity<ResponseDto<List<IbanTransactionDto>>> getIbanTransaction(@RequestHeader (HttpHeaders.AUTHORIZATION) String token) { //@RequestHeader serve per recuperare un parametro dell'header specifico, in questo caso Authorization

        String fiscalCode = jwtGenerator.getFiscalCodeFromJWT(token);

        List<IbanTransactionDto> ibanTransactionDtoList = ibanTransactionService.getIbanTransaction(fiscalCode);

        HttpStatus httpStatus = HttpStatus.OK;

        if (ibanTransactionDtoList.isEmpty()) {
            httpStatus = HttpStatus.NO_CONTENT;
        }


        return new ResponseEntity<>(new ResponseDto<>(ibanTransactionDtoList, httpStatus, true), HttpStatus.OK);
    }


    /**
     * Chiamata per effettuare un bonifico
     *
     * @param transferDto nel body della chiamata passo in input amount, descriptionTransaction, beneficiary
     * @return Ritorna una ResponseEntity con un messaggio di "bonifico avvenuto"
     * @throw TransferException Se l'amount in inputo è < di 1,
     * se l'iban del beneficiario e dell'ordinario non esistono
     * se il balance dell'ordinario è < di 0
     * se il nome e cognome del beneficiario e dell'ordinario non matchato
     */
    @PostMapping("/transfer")
    //Qui invece, grazie al generics abbiamo deciso di far tornare un semplice messaggio di avvenuto bonifico, quindi una String
    public ResponseEntity<ResponseDto<String>> makeTransfer(@RequestBody TransferDto transferDto, @RequestHeader (HttpHeaders.AUTHORIZATION) String token) {

        try {
            ibanTransactionService.makeTransfers(transferDto, token);
            return new ResponseEntity<>(new ResponseDto<>("Success transfer!",
                    HttpStatus.OK,
                    true), HttpStatus.OK);

        } catch (TransferException ex) {// SEMPRE DALLA PIU SPECIFICA A QUELLA PIU GENERICA (EXCEPTION) !!!!!!
            return new ResponseEntity<>(new ResponseDto<>(ex.getMessage(),
                    HttpStatus.BAD_REQUEST,
                    false), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ResponseDto<>(ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    false), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
