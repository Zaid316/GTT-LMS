package com.hexaware.gtt.lms.dto;

import java.util.UUID;

public class CouponGenerationDto {
	
	private UUID couponId;
	 private UUID uId;
	public CouponGenerationDto(UUID couponId, UUID uId) {
		super();
		this.couponId = couponId;
		this.uId = uId;
	}
	public CouponGenerationDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UUID getCouponId() {
		return couponId;
	}
	public void setCouponId(UUID couponId) {
		this.couponId = couponId;
	}
	public UUID getuId() {
		return uId;
	}
	public void setuId(UUID uId) {
		this.uId = uId;
	}
	 
}
