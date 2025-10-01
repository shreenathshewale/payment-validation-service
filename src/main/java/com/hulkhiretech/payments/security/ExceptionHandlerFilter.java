package com.hulkhiretech.payments.security;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.gson.Gson;
import com.hulkhiretech.payments.constant.ErrorEnum;
import com.hulkhiretech.payments.exception.ValidationException;
import com.hulkhiretech.payments.pojo.ErrorResponse;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class ExceptionHandlerFilter extends OncePerRequestFilter {
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			log.info(" ExceptionHandlerFilter Before doFilter");
			filterChain.doFilter(request, response);
			log.info(" ExceptionHandlerFilter After doFilter");
		} catch (ValidationException ex) {
			log.error(" ValidationException message is -> " + ex.getMessage());
			
			ErrorResponse paymentResponse = new ErrorResponse(
					ex.getErrorCode(),
					ex.getErrorMessage());
			
			log.error(" paymentResponse is -> " + paymentResponse);
			
			Gson gson = new Gson();
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.setContentType("application/json");
			response.getWriter().write(gson.toJson(paymentResponse));
			response.getWriter().flush();
			
		} catch (Exception ex) {
			log.error(" generic exception message is -> " + ex.getMessage());
			
			ErrorResponse paymentResponse = new ErrorResponse(
					ErrorEnum.GENERIC_ERROR.getErrorCode(),
					ErrorEnum.GENERIC_ERROR.getErrorMessage());
			
			log.error(" paymentResponse is -> " + paymentResponse);
			Gson gson = new Gson();
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setContentType("application/json");
			response.getWriter().write(gson.toJson(paymentResponse));
			response.getWriter().flush();
		}
	}
}
