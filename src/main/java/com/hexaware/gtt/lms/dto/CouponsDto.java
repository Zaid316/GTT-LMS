package com.hexaware.gtt.lms.dto;
 
import java.util.UUID;
 
import com.hexaware.gtt.lms.entities.Tiers;
 
public class CouponsDto {
	
	private UUID couponId;
    private UUID tierId;
 
	private String couponTitle;
	private String couponDescription;
	private int validity;
	private double maxLimit;
	private double percentage;
 
	private boolean status;
 
    
   
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
 
    public boolean isStatus() {
        return status;
    }
 
    public void setStatus(boolean status) {
        this.status = status;
    }
 
	public UUID getCouponId() {
		return couponId;
	}
 
	public void setCouponId(UUID couponId) {
		this.couponId = couponId;
	}
 
	public UUID getTierId() {
		return tierId;
	}
 
	public void setTierId(UUID tierId) {
		this.tierId = tierId;
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
	
	
 
}
 
 