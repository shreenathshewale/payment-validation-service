package com.hulkhiretech.payments.service.interfaces;

import com.hulkhiretech.payments.pojo.PaymentRequest;

public interface Validator {
	
	public void validate(PaymentRequest paymentRequest);

}
