package com.hulkhiretech.payments.util;

import java.io.BufferedReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.hulkhiretech.payments.pojo.PaymentRequest;
import com.hulkhiretech.payments.security.WrappedRequest;

public class RequestUtil {
	
	private static final Gson gson = new Gson();

    public static PaymentRequest extractPaymentRequest(WrappedRequest request) throws IOException {
        StringBuilder jsonBuilder = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
        }
        String json = jsonBuilder.toString();
        return gson.fromJson(json, PaymentRequest.class);
    }

}
