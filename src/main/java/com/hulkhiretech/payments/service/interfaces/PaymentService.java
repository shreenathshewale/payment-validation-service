package com.hulkhiretech.payments.service.interfaces;

import com.hulkhiretech.payments.pojo.PaymentRequest;
import com.hulkhiretech.payments.pojo.PaymentResponse;

public interface PaymentService {
	
	public PaymentResponse createPayment(PaymentRequest paymentDetails);

}
