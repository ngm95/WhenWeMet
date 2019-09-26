package com.spring.project.exception;

public class AlreadyExistingMnameException extends RuntimeException {
	public AlreadyExistingMnameException(String message) {
		super(message);
	}
}
