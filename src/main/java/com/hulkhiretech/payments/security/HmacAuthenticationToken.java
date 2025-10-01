package com.hulkhiretech.payments.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class HmacAuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = 1633144925270974106L;

	private final Object credentials;
	private final Object principal;
	
	public HmacAuthenticationToken(Object principal, Object credentials) {
		super(null);
		this.principal = principal;
		this.credentials = credentials;
		setAuthenticated(true);
	}
	
	
	@Override
	public Object getCredentials() {
		return credentials;
	}
	@Override
	public Object getPrincipal() {
		return principal;
	}
}
