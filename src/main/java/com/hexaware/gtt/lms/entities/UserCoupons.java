package com.hexaware.gtt.lms.entities;

import java.time.LocalDateTime;

import com.hexaware.gtt.lms.enums.UserCouponStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UserCoupons {

	@Id
	private String couponCode;
	
	@ManyToOne
	@JoinColumn(name = "couponId")
	private Coupons coupons;

	@ManyToOne
	@JoinColumn(name = "u_id")
	private Users users;

	@Column(updatable = false)
	private LocalDateTime issuedOn;

	@Enumerated(EnumType.STRING)
	private UserCouponStatus status = UserCouponStatus.ACTIVE;

	private LocalDateTime expiry;
	private LocalDateTime couponUsedDate;

	

	public UserCoupons() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserCoupons(String couponCode, Coupons coupons, Users users, LocalDateTime issuedOn, UserCouponStatus status,
			LocalDateTime expiry) {
		super();
		this.couponCode = couponCode;
		this.coupons = coupons;
		this.users = users;
		this.issuedOn = issuedOn;
		this.status = status;
		this.expiry = expiry;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public Coupons getCouponId() {
		return coupons;
	}

	public void setCoupons(Coupons coupons) {
		this.coupons = coupons;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
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



}