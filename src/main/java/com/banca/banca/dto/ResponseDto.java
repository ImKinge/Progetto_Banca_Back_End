package com.banca.banca.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Dto che mi permette di mappare la risposta dentro un json
 * e riutilizzabile per tutte le ResponseEntity dei Controller
 * @param <T>
 */
@Getter
public class ResponseDto <T> {

    /**
     * Body della risposta
     */
    private T responseBody;

    private HttpStatus httpStatus;

    private Boolean success;


    //Constructor
    public ResponseDto() {
    }

    public ResponseDto(T responseBody, HttpStatus httpStatus, Boolean success) {
        this.responseBody = responseBody;
        this.httpStatus = httpStatus;
        this.success = success;
    }

    //Getter and Setter
    public void setResponseBody(T responseBody) {
        this.responseBody = responseBody;
    }


    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }


    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
