package com.hulkhiretech.payments.service.impl.validator;

import org.springframework.stereotype.Service;

import com.hulkhiretech.payments.constant.ErrorEnum;
import com.hulkhiretech.payments.exception.ValidationException;
import com.hulkhiretech.payments.pojo.PaymentRequest;
import com.hulkhiretech.payments.service.interfaces.Validator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Check1Validator implements	Validator{

	@Override
	public void validate(PaymentRequest paymentRequest) {
		log.info("Validating payment request: {}", paymentRequest);
		// TODO Auto-generated method stub
		
		// TEMP logic to throw exception
		if (paymentRequest.getCustomerId() == null 
				|| paymentRequest.getCustomerId().isEmpty()) {
			
			log.error("Customer ID is missing in the payment request");
		    
			ValidationException customeIdException = new ValidationException(
					ErrorEnum.MISSING_CUSTOMER_ID.getErrorCode(), 
					ErrorEnum.MISSING_CUSTOMER_ID.getErrorMessage());
		    
		    log.error("Validation failed: {}", customeIdException.getMessage());
			throw customeIdException;
		}
		
	}

}
