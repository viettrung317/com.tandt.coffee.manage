package com.tandt.coffee.manage.api.payload;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseFactory {

	public static <T> ResponseEntity<ApiResponse<T>> success(String message, T data) {
		return ResponseEntity.ok(new ApiResponse<>(true, message, data));
	}

	public static <T> ResponseEntity<ApiResponse<T>> fail(String message, HttpStatus status) {
		return ResponseEntity.status(status).body(new ApiResponse<>(false, message, null));
	}
}
