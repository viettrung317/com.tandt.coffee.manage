package com.tandt.coffee.manage.api.exceptions;

public class EntityAlreadyExistException extends RuntimeException {

	/**
	 * 
	 */
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
