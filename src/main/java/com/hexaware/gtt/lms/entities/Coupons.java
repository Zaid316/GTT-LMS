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
	private double maxLimit;
	private double percentage;
 
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
 
	public double getMaxLimit() {
		return maxLimit;
	}
 
	public void setMaxLimit(double maxLimit) {
		this.maxLimit = maxLimit;
	}
 
	public double getPercentage() {
		return percentage;
	}
 
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
 
	public boolean getStatus() {
		return status;
	}
 
	public void setStatus(boolean status) {
		this.status = status;
	}
 
	public Coupons(UUID couponId, Tiers tiers, String couponTitle, String couponDescription, int validity,
			double maxLimit, double percentage, boolean status) {
		super();
		this.couponId = couponId;
		this.tiers = tiers;
		this.couponTitle = couponTitle;
		this.couponDescription = couponDescription;
		this.validity = validity;
		this.maxLimit = maxLimit;
		this.percentage = percentage;
		this.status = status;
	}

	public Coupons() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
 
}
 
 