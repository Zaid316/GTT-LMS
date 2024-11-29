package com.hexaware.gtt.lms.dto;
 
import java.util.UUID;

import com.hexaware.gtt.lms.entities.Partner;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
 
public class TiersDto {
	private UUID tierId;
	@NotEmpty
    private String tierName;
	@NotNull
    private double triggerAmount;
	@NotNull
    private int triggerDuration;/* no. of months the tiers will be available*/
	@NotNull
    private double accrualMultiplier;/* for the multiplier of points*/
	@NotNull
    private int redemptionLimitOfPurchase; /* limit of how much points can be spent for purchase*/
	@NotNull
    private double conversion; /* point to money value */
	@NotEmpty
    private String description;
	@NotNull
    private float couponProbability;/* the pblty of getting prize*/

    private String colour;

    private UUID partner_id;
    
    public String getColour() {
  		return colour;
  	}

  	public void setColour(String colour) {
  		this.colour = colour;
  	}
 
    public String getTierName() {
        return tierName;
    }
 
    public void setTierName(String tierName) {
        this.tierName = tierName;
    }
 
    public double getTriggerAmount() {
        return triggerAmount;
    }
 
    public void setTriggerAmount(double triggerAmount) {
        this.triggerAmount = triggerAmount;
    }
 
    public int getTriggerDuration() {
        return triggerDuration;
    }
 
    public void setTriggerDuration(int triggerDuration) {
        this.triggerDuration = triggerDuration;
    }
 
    public double getAccrualMultiplier() {
        return accrualMultiplier;
    }
 
    public void setAccrualMultiplier(double accrualMultiplier) {
        this.accrualMultiplier = accrualMultiplier;
    }
 
    public int getRedemptionLimitOfPurchase() {
        return redemptionLimitOfPurchase;
    }
 
    public void setRedemptionLimitOfPurchase(int redemptionLimitOfPurchase) {
        this.redemptionLimitOfPurchase = redemptionLimitOfPurchase;
    }
 
    public double getConversion() {
        return conversion;
    }
 
    public void setConversion(double conversion) {
        this.conversion = conversion;
    }
 
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
 
    public float getCouponProbability() {
        return couponProbability;
    }
 
    public void setCouponProbability(float couponProbability) {
        this.couponProbability = couponProbability;
    }
 
    
	
 
	public UUID getPartner_id() {
		return partner_id;
	}
	public UUID getTierId() {
		return tierId;
	}

	public void setTierId(UUID tierId) {
		this.tierId = tierId;
	}

	public void setPartner_id(UUID partner_id) {
		this.partner_id = partner_id;
	}

	public TiersDto() {
		super();
		// TODO Auto-generated constructor stub
	}
 
	public TiersDto(UUID tierId,String tierName, double triggerAmount, int triggerDuration, double accrualMultiplier,
			int redemptionLimitOfPurchase, double conversion, String description, float couponProbability,
			UUID partner_id) {
		super();
		this.tierId = tierId;
		this.tierName = tierName;
		this.triggerAmount = triggerAmount;
		this.triggerDuration = triggerDuration;
		this.accrualMultiplier = accrualMultiplier;
		this.redemptionLimitOfPurchase = redemptionLimitOfPurchase;
		this.conversion = conversion;
		this.description = description;
		this.couponProbability = couponProbability;
		this.partner_id = partner_id;
	}
	
	
 
}