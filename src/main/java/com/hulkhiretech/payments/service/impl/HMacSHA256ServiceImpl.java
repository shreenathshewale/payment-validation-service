package com.hulkhiretech.payments.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.hulkhiretech.payments.constant.ErrorEnum;
import com.hulkhiretech.payments.exception.ValidationException;
import com.hulkhiretech.payments.pojo.PaymentRequest;
import com.hulkhiretech.payments.service.interfaces.HMacSHA256Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class HMacSHA256ServiceImpl implements HMacSHA256Service {

	private final Gson gson;
	
	@Value("${merchant.client.id}")
	private String clientId;

	@Override
	public String generateHmacSHA256Signature(String jsonData) {

		String secretKey = "THIS_IS_MY_SECRET";
		String signature = null;

		try {
			// Create a SecretKeySpec object from the secret key
			SecretKeySpec keySpec = new SecretKeySpec(
					secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");

			// Initialize the HMAC-SHA256 Mac instance
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(keySpec);

			// Compute the HMAC-SHA256 signature
			byte[] signatureBytes = mac.doFinal(
					jsonData.getBytes(StandardCharsets.UTF_8));

			// Encode the signature in Base64
			signature = Base64.getEncoder(
					).encodeToString(signatureBytes);

		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			e.printStackTrace();
		}
		log.info("Generated HMAC-SHA256 signature: {}", signature);
		return signature;
	}

	@Override
	public void verifyHmacSignature(String incomingHmacSignature, 
			PaymentRequest paymentDetails) {

		if (incomingHmacSignature == null 
				|| incomingHmacSignature.isEmpty()) {
			log.error("Incoming HMAC signature is null or empty");

			throw new ValidationException(
					ErrorEnum.MISSING_HMAC_SIGNATURE.getErrorCode(), 
					ErrorEnum.MISSING_HMAC_SIGNATURE.getErrorMessage());
		}
		
		log.info("Verifying HMAC signature: {}", incomingHmacSignature);

		String jsonReq = gson.toJson(paymentDetails);
		
		String generatedHmacSignature = generateHmacSHA256Signature(jsonReq);
		log.info("Generated HMAC signature: {}", generatedHmacSignature);
		
		if (!incomingHmacSignature.equals(generatedHmacSignature)) {
			log.error("HMAC signature verification failed. "
					+ "incomingHmacSignature: {}, generatedHmacSignature: {}", 
					incomingHmacSignature,
					generatedHmacSignature);

			throw new ValidationException(
					ErrorEnum.INVALID_HMAC_SIGNATURE.getErrorCode(),
					ErrorEnum.INVALID_HMAC_SIGNATURE.getErrorMessage());
		}
		
		log.info("HMAC signature verification successful");
	}

	@Override
	public String getClientId() {
		return clientId;
	}

}
