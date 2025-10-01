package com.hulkhiretech.payments.exception;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {
	private static final long serialVersionUID = -6560387861714534572L;

	private final String errorCode;
    private final String errorMessage;

    public ValidationException(String errorCode, String errorMessage) {
        super(errorMessage); // Optional: to include message in stack trace
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
