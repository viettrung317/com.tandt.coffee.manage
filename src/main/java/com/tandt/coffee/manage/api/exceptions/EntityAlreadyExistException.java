package com.tandt.coffee.manage.api.exceptions;

import java.io.Serial;

public class EntityAlreadyExistException extends RuntimeException {

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -5304971467335441322L;

	public EntityAlreadyExistException(Long Id) {
		super("Entity already exists: " + Id);
	}
	
	// Additional constructors for flexibility
    public EntityAlreadyExistException(String message) {
        super(message);
    }
    
    public EntityAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
