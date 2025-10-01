package com.hulkhiretech.payments.service.interfaces;

import com.hulkhiretech.payments.pojo.PaymentRequest;

public interface HMacSHA256Service {
	
	public String generateHmacSHA256Signature(String jsonData);
	
	public void verifyHmacSignature(String incomingHmacSignature, 
			PaymentRequest paymentDetails);
	
	public String getClientId();
}
