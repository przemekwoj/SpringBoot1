package com.przemo.RestAPI.exception.globalControllerAdvice;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.WebUtils;

import com.przemo.RestAPI.exception.ApiError;
import com.przemo.RestAPI.exception.ObjectNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler
{

	@ExceptionHandler({ ObjectNotFoundException.class,EmptyResultDataAccessException.class,MethodArgumentTypeMismatchException.class})
	 public  ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) 
	{
		HttpHeaders headers = new HttpHeaders();
		
		if (ex instanceof ObjectNotFoundException ) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            ObjectNotFoundException unfe = (ObjectNotFoundException) ex;
            return handleObjectNotFoundException(unfe, headers, status, request);
            
        }
        else if (ex instanceof EmptyResultDataAccessException) {
        	 HttpStatus status = HttpStatus.NOT_FOUND;
             ObjectNotFoundException unfe = (ObjectNotFoundException) ex;
             return handleObjectNotFoundException(unfe, headers, status, request);
            
        } 
        else if(ex instanceof MethodArgumentTypeMismatchException){
        	HttpStatus status = HttpStatus.NOT_FOUND;
            return handleBadPathVariable(ex, headers, status, request);
        }
        else {

            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, null, headers, status, request);
        }
	}

	private ResponseEntity<ApiError> handleBadPathVariable(Exception ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
        MethodArgumentTypeMismatchException mm = (MethodArgumentTypeMismatchException) ex;
        String error = "bad path variable :"+mm.getValue().toString(); 
		List<String> errors = Arrays.asList(error);
        status = HttpStatus.NOT_FOUND;
        return handleExceptionInternal(ex, new ApiError(errors,status), headers, status, request);
	}

	private ResponseEntity<ApiError> handleObjectNotFoundException(ObjectNotFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		List<String> errors = Collections.singletonList(ex.getMessage());
        status = HttpStatus.NOT_FOUND;
        return handleExceptionInternal(ex, new ApiError(errors,status), headers, status, request);
	}
		
	private ResponseEntity<ApiError> handleExceptionInternal(Exception ex, ApiError body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		 if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
	            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
	        }

	        return new ResponseEntity<>( body ,headers, status);
	}

	
}
