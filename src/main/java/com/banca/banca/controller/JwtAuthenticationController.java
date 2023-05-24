package com.banca.banca.controller;

import com.banca.banca.dto.AuthResponseDto;
import com.banca.banca.dto.BaseResponse;
import com.banca.banca.dto.CustomerDataDto;
import com.banca.banca.dto.ResponseDto;
import com.banca.banca.exception.CustomerDataException;
import com.banca.banca.exception.RoleException;
import com.banca.banca.model.JwtRequest;
import com.banca.banca.repository.CustomerDataRepository;
import com.banca.banca.security.jwt.JWTGenerator;
import com.banca.banca.service.CustomerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/authenticate")
@CrossOrigin(origins = "http://localhost:4200" , allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class JwtAuthenticationController {

	private final CustomerDataService customerDataService;
    private final CustomerDataRepository customerDataRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;

    @Autowired
    public JwtAuthenticationController(CustomerDataService customerDataService, CustomerDataRepository customerDataRepository, AuthenticationManager authenticationManager, JWTGenerator jwtGenerator) {
        this.customerDataService = customerDataService;
        this.customerDataRepository = customerDataRepository;
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
    }


    @PostMapping(value = "/register")
    public ResponseEntity<ResponseDto<String>> register(@RequestBody CustomerDataDto customerDataDto) throws RoleException {
        if (customerDataRepository.existsByUserName(customerDataDto.getUsername())) {
            return new ResponseEntity<>(new ResponseDto<>("Username is taken!", HttpStatus.BAD_REQUEST, false), HttpStatus.OK);
        }

        customerDataService.saveCustomer(customerDataDto);
        return new ResponseEntity<>(new ResponseDto<>("Customer registered success!", HttpStatus.OK, true), HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public BaseResponse<ResponseDto<AuthResponseDto>> login(@RequestBody JwtRequest jwtRequest) {

        ResponseDto<AuthResponseDto> responseDto = new ResponseDto<>();

        try{
            CustomerDataDto customerDataDto = customerDataService.findByUsername(jwtRequest.getUsername());
            String fiscalCode = customerDataDto.getFiscalCode();
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication.getName(), fiscalCode);
            AuthResponseDto authResponseDto = new AuthResponseDto(token);

            responseDto.setResponseBody(authResponseDto);
            responseDto.setHttpStatus(HttpStatus.OK);
            responseDto.setSuccess(true);
            return new BaseResponse<ResponseDto<AuthResponseDto>>().asSuccess(responseDto);
        } catch (CustomerDataException ex){

            return new BaseResponse().asError(ex.getMessage());
        }
    }
}
