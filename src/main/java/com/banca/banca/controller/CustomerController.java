package com.banca.banca.controller;

import com.banca.banca.dto.CustomerDataDto;
import com.banca.banca.dto.ResponseDto;
import com.banca.banca.exception.CustomerDataException;
import com.banca.banca.exception.RoleException;
import com.banca.banca.model.JwtRequest;
import com.banca.banca.service.CustomerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:4200" , allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class CustomerController {

    @Autowired
    private CustomerDataService customerDataService;


    @GetMapping("/find-customer/{fiscalCode}")
    public ResponseEntity<ResponseDto<CustomerDataDto>> findByFiscalCode (@PathVariable String fiscalCode) {
        try {
            CustomerDataDto customerDataDto = customerDataService.findByFiscalCode(fiscalCode);
            return new ResponseEntity<>(new ResponseDto<>(customerDataDto, HttpStatus.OK, true), HttpStatus.OK);
        } catch (CustomerDataException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //POST
    @PostMapping("/save")
    public ResponseEntity<ResponseDto<String>> saveCliente(@RequestBody CustomerDataDto customerDataDto) throws RoleException {
        customerDataService.saveCustomer(customerDataDto);

        return new ResponseEntity<>(new ResponseDto<>("Registrazione avvenuta con successo!", HttpStatus.OK, true), HttpStatus.OK);
    }

}
