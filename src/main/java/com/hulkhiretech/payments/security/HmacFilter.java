package com.hulkhiretech.payments.security;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.gson.Gson;
import com.hulkhiretech.payments.constant.Constant;
import com.hulkhiretech.payments.pojo.PaymentRequest;
import com.hulkhiretech.payments.service.interfaces.HMacSHA256Service;
import com.hulkhiretech.payments.util.RequestUtil;

import ch.qos.logback.core.testUtil.RandomUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HmacFilter extends OncePerRequestFilter {

	private HMacSHA256Service hMacSHA256Service;
	
	private Gson gson;

	public HmacFilter(HMacSHA256Service hMacSHA256Service, Gson gson) {
		this.hMacSHA256Service = hMacSHA256Service;
		this.gson = gson;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest requestRef, 
			HttpServletResponse response, 
			FilterChain filterChain)
					throws ServletException, IOException {
		log.info("HmacFilter: Processing request");
		WrappedRequest wrappedRequest = new WrappedRequest(requestRef);

		String incomingHmacSignature = wrappedRequest.getHeader(
				Constant.HMAC_SIGNATURE); 

		PaymentRequest reqObj = getReqObjFromServletReq(wrappedRequest);
		PaymentRequest convertedResponse = RequestUtil.extractPaymentRequest(wrappedRequest);
		log.info("HmacFilter: Converted PaymentRequest: {}", convertedResponse);
		

		log.info("HmacFilter: Extracted PaymentRequest: {}", reqObj);

		// If below method throws exception, means invalid, else valid.
		hMacSHA256Service.verifyHmacSignature(
				incomingHmacSignature, reqObj);

		// ONLY VALID REQUESTS WILL REACH HERE.
		log.info("HmacFilter: HMAC signature verified successfully");

		// Request is authenticated, set the security context.
		SecurityContext context = SecurityContextHolder.createEmptyContext(); 
		Authentication authentication = new HmacAuthenticationToken(
				hMacSHA256Service.getClientId(), incomingHmacSignature);
		context.setAuthentication(authentication);
		SecurityContextHolder.setContext(context);
		log.info("HmacFilter: Security context set with authentication");

		filterChain.doFilter(wrappedRequest, response); 

		log.info("HmacFilter: Filter chain processed successfully");
	}

	private PaymentRequest getReqObjFromServletReq(WrappedRequest wrappedRequest) {
		PaymentRequest reqObj = gson.fromJson(
				new InputStreamReader(wrappedRequest.getInputStream(), StandardCharsets.UTF_8),
				PaymentRequest.class
				);
		return reqObj;
	}

}
