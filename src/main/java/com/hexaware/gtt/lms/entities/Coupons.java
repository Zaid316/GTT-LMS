package com.hexaware.gtt.lms.entities;

import java.util.UUID;
 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
 
@Entity
public class Coupons {
 
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID couponId;
 
	@ManyToOne
	@JoinColumn(name = "tier_id")
	private Tiers tiers;
 
	private String couponTitle;
	private String couponDescription;
	private int validity;
	private int benefits;
 
	private boolean status;
 
 
	public UUID getCouponId() {
		return couponId;
	}
 
	public void setCouponId(UUID couponId) {
		this.couponId = couponId;
	}
 
	public Tiers getTiers() {
		return tiers;
	}
 
	public void setTiers(Tiers tiers) {
		this.tiers = tiers;
	}
 
	public String getCouponTitle() {
		return couponTitle;
	}
 
	public void setCouponTitle(String couponTitle) {
		this.couponTitle = couponTitle;
	}
 
	public String getCouponDescription() {
		return couponDescription;
	}
 
	public void setCouponDescription(String couponDescription) {
		this.couponDescription = couponDescription;
	}
 
	public int getValidity() {
		return validity;
	}
 
	public void setValidity(int validity) {
		this.validity = validity;
	}
 
	public int getBenefits() {
		return benefits;
	}
 
	public void setBenefits(int benefits) {
		this.benefits = benefits;
	}
 
	public boolean getStatus() {
		return status;
	}
 
	public void setStatus(boolean status) {
		this.status = status;
	}
 
}
