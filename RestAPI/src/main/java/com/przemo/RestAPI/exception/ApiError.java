package com.przemo.RestAPI.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

public class ApiError {
    private List<String> errors;
    
    private HttpStatus status;
    
	public ApiError(List<String> errors) {
        this.errors = errors;
    }
	public ApiError(List<String> errors,HttpStatus status) {
        this.errors = errors;
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
    
    
}