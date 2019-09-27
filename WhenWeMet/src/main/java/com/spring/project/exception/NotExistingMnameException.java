package com.spring.project.exception;

public class NotExistingMnameException extends RuntimeException {
	public NotExistingMnameException(String message) {
		super(message);
	}
}
