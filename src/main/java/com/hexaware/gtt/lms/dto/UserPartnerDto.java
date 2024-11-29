package com.hexaware.gtt.lms.dto;
 
import java.util.UUID;
 
public class UserPartnerDto {
	
	private long userId;
	private UUID partnerId;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public UUID getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(UUID partnerId) {
		this.partnerId = partnerId;
	}
	public UserPartnerDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserPartnerDto(long userId, UUID partnerId) {
		super();
		this.userId = userId;
		this.partnerId = partnerId;
	}
	
	
	
 
}