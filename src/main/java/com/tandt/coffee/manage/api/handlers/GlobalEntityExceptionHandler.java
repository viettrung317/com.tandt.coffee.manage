package com.tandt.coffee.manage.api.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tandt.coffee.manage.api.exceptions.EntityAlreadyExistException;
import com.tandt.coffee.manage.api.exceptions.EntityNotFoundException;
import com.tandt.coffee.manage.api.payload.ApiResponse;
import com.tandt.coffee.manage.api.payload.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalEntityExceptionHandler {
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrderNotFound(EntityNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            "Entity Not Found",
            ex.getMessage(),
            System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
	@ExceptionHandler(EntityAlreadyExistException.class)
	public ResponseEntity<ApiResponse<Void>> handleOrderExists(EntityAlreadyExistException ex) {
		log.warn("Order already exists: {}", ex.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>(false, ex.getMessage(), null));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Void>> handleOtherErrors(Exception ex) {
		log.error("UnExpected error while creating order", ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ApiResponse<>(false, "Unexpected error occurred while creating order", null));
	}
}
