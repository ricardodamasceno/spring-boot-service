package com.spring.adapters.config;

import com.spring.adapters.vo.response.CustomExceptionResponse;
import com.spring.domain.exception.PurchaseItemCreationException;
import com.spring.domain.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<CustomExceptionResponse> handleAllException(Exception ex, WebRequest request){
        return new ResponseEntity<>(
                new CustomExceptionResponse(ex.getMessage()),
                getResponseStatus(ex));
    }

    private HttpStatus getResponseStatus(Exception exception){
        if(exception instanceof HttpMessageNotReadableException ||
                exception instanceof HttpRequestMethodNotSupportedException){
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    // #################### Purchase Item Exceptions ####################

    @ExceptionHandler(value = PurchaseItemCreationException.class)
    protected ResponseEntity<CustomExceptionResponse> handlePurchaseItemCreationFailure(Exception ex, WebRequest request){
        CustomExceptionResponse response = new CustomExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // #################### User Exceptions ####################

    @ExceptionHandler(value = UserNotFoundException.class)
    protected ResponseEntity<CustomExceptionResponse> handleUserNotFound(Exception ex, WebRequest request){
        CustomExceptionResponse response = new CustomExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
