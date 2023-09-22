package com.banca.banca.dto;

import lombok.Getter;
import java.util.List;

@Getter
public class BaseResponse<T> {

//    boolean success;
//    List<String> errors;
    T res;

//    public boolean isSuccess() {
//        return success;
//    }
//
//    public void setSuccess(boolean success) {
//        this.success = success;
//    }
//
//    public List<String> getErrors() {
//        return errors;
//    }
//
//    public void setErrors(List<String> errors) {
//        this.errors = errors;
//    }

    public void setRes(T res) {
        this.res = res;
    }

    public BaseResponse<T> asSuccess(T res){

//        setSuccess(true);
        this.setRes(res);

        return this;
    }

    public BaseResponse<T> asSuccess(){
//        setSuccess(true);
        return this;
    }

    public BaseResponse<T> withResult(T res){
        this.setRes(res);
        return this;
    }

    public BaseResponse<T> asError(T res){
        this.setRes(res);
        return this;
    }

    public BaseResponse<T> asError(List<String> errors){
//        setErrors(errors);
        return this;
    }
}
