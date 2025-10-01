package com.hulkhiretech.payments.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.session.DisableEncodeUrlFilter;

import com.google.gson.Gson;
import com.hulkhiretech.payments.security.ExceptionHandlerFilter;
import com.hulkhiretech.payments.security.HmacFilter;
import com.hulkhiretech.payments.service.interfaces.HMacSHA256Service;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
	
	private final HMacSHA256Service hMacSHA256Service;
	
	private final Gson gson;

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
	    .csrf(csrf -> csrf.disable())
	    
	    .addFilterBefore(new ExceptionHandlerFilter(), DisableEncodeUrlFilter.class)
	    
	    .addFilterAfter(new HmacFilter(hMacSHA256Service, gson), LogoutFilter.class) // Ensure HmacFilter runs after LogoutFilter)
	    
	    .authorizeHttpRequests(authorize -> authorize
	    		.anyRequest().authenticated())
	    
	    .sessionManagement(session -> session
	    		.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
