package com.hexaware.gtt.lms.dto;
 
import java.util.UUID;
 
public class Logindto {
 
	private UUID partnerId;
	private String email;
	private boolean status;
	private String partnerName;
	private Long contact;
	private int countryCode;
	
	
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public Long getContact() {
		return contact;
	}
	public void setContact(Long contact) {
		this.contact = contact;
	}
	public int getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public UUID getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(UUID partnerId) {
		this.partnerId = partnerId;
		
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
 

