package com.hexaware.gtt.lms.dto;

import java.util.UUID;

import com.hexaware.gtt.lms.entities.Tiers;

public class CouponsResponseDto {

	    private Tiers tiers;
	    private UUID couponId;
		private String couponTitle;
		private String couponDescription;
		private int validity;
		private double percentage;
		private double maxLimit;
	 
		private boolean status;

	    
	   

		public Tiers getTiers() {
			return tiers;
		}

		public void setTiers(Tiers tiers) {
			this.tiers = tiers;
		}

		

		public UUID getCouponId() {
			return couponId;
		}

		public void setCouponId(UUID couponId) {
			this.couponId = couponId;
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



	    public double getPercentage() {
			return percentage;
		}

		public void setPercentage(double percentage) {
			this.percentage = percentage;
		}

		public double getMaxLimit() {
			return maxLimit;
		}

		public void setMaxLimit(double maxLimit) {
			this.maxLimit = maxLimit;
		}

		public boolean isStatus() {
	        return status;
	    }

	    public void setStatus(boolean status) {
	        this.status = status;
	    }

}
