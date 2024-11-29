package com.hexaware.gtt.lms.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class FreeTiersDto {
	private String tierName;
	
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

	public String getTierName() {
		return tierName;
	}

	public void setTierName(String tierName) {
		this.tierName = tierName;
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

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public UUID getPartner_id() {
		return partner_id;
	}

	public void setPartner_id(UUID partner_id) {
		this.partner_id = partner_id;
	}
    
    
}
