package com.hulkhiretech.payments.constant;

import lombok.Getter;

@Getter
public enum ErrorEnum {
    GENERIC_ERROR("10000", "Unable to process your request, please try later"),
    MISSING_CUSTOMER_ID("10001", "Customer ID is missing in the payment request"),
    MISSING_HMAC_SIGNATURE("10002", "HMAC signature is missing in the payment request"), 
    INVALID_HMAC_SIGNATURE("10003", "HMAC signature is invalid. Please check & try again");

    private final String errorCode;
    private final String errorMessage;

    ErrorEnum(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}

