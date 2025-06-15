package com.tandt.coffee.manage.api.exceptions;

import java.io.Serial;

public class EntityNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -4717569202536449809L;

	public EntityNotFoundException(Long id) {
		super("Product not found with ID: "+id);
	}
	
    // Additional constructors for flexibility
    public EntityNotFoundException(String message) {
        super(message);
    }
    
    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
