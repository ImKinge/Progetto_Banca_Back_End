package com.banca.banca.dto;

import org.springframework.http.HttpStatus;

/**
 * Dto che mi permette di mappare la risposta dentro un json
 * e riutilizzabile per tutte le ResponseEntity dei Controller
 * @param <T>
 */
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
    public T getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(T responseBody) {
        this.responseBody = responseBody;
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
