package com.hexaware.gtt.lms.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.hexaware.gtt.lms.entities.Coupons;
import com.hexaware.gtt.lms.entities.Users;
import com.hexaware.gtt.lms.enums.UserCouponStatus;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class UserCouponDto {
	
	@Id
	private String couponCode;
	private String couponTitle;


	private UUID uId;

	private UUID couponId;

	@Column(updatable = false)
	private LocalDateTime issuedOn;

	@Enumerated(EnumType.STRING)
	private UserCouponStatus status;

	private LocalDateTime expiry;
	private LocalDateTime couponUsedDate;
	public UserCouponDto(String couponCode, UUID uId, UUID couponId, LocalDateTime issuedOn, UserCouponStatus status,
			LocalDateTime expiry, LocalDateTime couponUsedDate,String couponTitle) {
		super();
		this.couponCode = couponCode;
		this.uId = uId;
		this.couponId = couponId;
		this.issuedOn = issuedOn;
		this.status = status;
		this.expiry = expiry;
		this.couponUsedDate = couponUsedDate;
		this.couponTitle = couponTitle;
	}
	public UserCouponDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public UUID getuId() {
		return uId;
	}
	public void setuId(UUID uId) {
		this.uId = uId;
	}
	public UUID getCouponId() {
		return couponId;
	}
	public void setCouponId(UUID couponId) {
		this.couponId = couponId;
	}
	public LocalDateTime getIssuedOn() {
		return issuedOn;
	}
	public void setIssuedOn(LocalDateTime issuedOn) {
		this.issuedOn = issuedOn;
	}
	public UserCouponStatus getStatus() {
		return status;
	}
	public void setStatus(UserCouponStatus status) {
		this.status = status;
	}
	public LocalDateTime getExpiry() {
		return expiry;
	}
	public void setExpiry(LocalDateTime expiry) {
		this.expiry = expiry;
	}
	public LocalDateTime getCouponUsedDate() {
		return couponUsedDate;
	}
	public void setCouponUsedDate(LocalDateTime couponUsedDate) {
		this.couponUsedDate = couponUsedDate;
	}
	public String getCouponTitle() {
		return couponTitle;
	}
	public void setCouponTitle(String couponTitle) {
		this.couponTitle = couponTitle;
	}

	

	
}
