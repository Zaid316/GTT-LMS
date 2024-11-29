package com.hexaware.gtt.lms.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Tiers {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID tierId;

    @ManyToOne
    @JoinColumn(name = "partner_id", nullable=false, updatable=false)
    private Partner partner; /* the  partner_id is changed to partner*/

    private String tierName;
    private double triggerAmount;
    private int triggerDuration; //days
    private double accrualMultiplier;
    private int redemptionLimitOfPurchase;
    private double conversion;
    private String description;
    private float couponProbability;
    private String colour;

    public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public UUID getTierId() {
        return tierId;
    }

    public void setTierId(UUID tierId) {
        this.tierId = tierId;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getConversion() {
        return conversion;
    }

    public void setConversion(double conversion) {
        this.conversion = conversion;
    }

    public float getCouponProbability() {
        return couponProbability;
    }

    public void setCouponProbability(float couponProbability) {
        this.couponProbability = couponProbability;
    }

    
}
