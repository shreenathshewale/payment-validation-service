package com.hulkhiretech.payments.service.impl.validator;

import org.springframework.stereotype.Service;

import com.hulkhiretech.payments.pojo.PaymentRequest;
import com.hulkhiretech.payments.service.interfaces.Validator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Check2Validator implements	Validator{

	@Override
	public void validate(PaymentRequest paymentRequest) {
		log.info("Validating payment request: {}", paymentRequest);
		// TODO Auto-generated method stub
		
	}

}
