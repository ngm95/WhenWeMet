package com.spring.project.exception;

public class IdPasswordNotMatchingException extends RuntimeException {
	public IdPasswordNotMatchingException(String message) {
		super(message);
	}
}
