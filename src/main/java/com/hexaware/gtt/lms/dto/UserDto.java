package com.hexaware.gtt.lms.dto;

import java.time.LocalDateTime;

import com.hexaware.gtt.lms.entities.Tiers;

public class UserDto {

    private long userId;
    private Tiers tiers;
    private int totalPoints;
    private LocalDateTime expiry;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Tiers getTiers() {
        return tiers;
    }

    public void setTiers(Tiers tiers) {
        this.tiers = tiers;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public LocalDateTime getExpiry() {
        return expiry;
    }

    public void setExpiry(LocalDateTime expiry) {
        this.expiry = expiry;
    }
    
}