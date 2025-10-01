package com.hulkhiretech.payments.pojo;

public class PaymentResponse {
	
	private String id;
	private String redirectUrl;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	@Override
	public String toString() {
		return "PaymentResponse [id=" + id + ", redirectUrl=" + redirectUrl + "]";
	}
	
}
