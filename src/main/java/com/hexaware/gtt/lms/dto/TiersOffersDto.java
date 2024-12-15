package com.hexaware.gtt.lms.dto;

public class TiersOffersDto {
	
	private String currentTier;
	private String nextTier;
	private Double totalPoints;
	private Double leftPointsToReachNextTier;
	
	public TiersOffersDto(String currentTier, String nextTier, Double totalPoints, Double leftPointsToReachNextTier) {
		super();
		this.currentTier = currentTier;
		this.nextTier = nextTier;
		this.totalPoints = totalPoints;
		this.leftPointsToReachNextTier = leftPointsToReachNextTier;
	}
	public TiersOffersDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCurrentTier() {
		return currentTier;
	}
	public void setCurrentTier(String currentTier) {
		this.currentTier = currentTier;
	}
	public String getNextTier() {
		return nextTier;
	}
	public void setNextTier(String nextTier) {
		this.nextTier = nextTier;
	}
	public Double getTotalPoints() {
		return totalPoints;
	}
	public void setTotalPoints(Double totalPoints) {
		this.totalPoints = totalPoints;
	}
	public Double getLeftPointsToReachNextTier() {
		return leftPointsToReachNextTier;
	}
	public void setLeftPointsToReachNextTier(Double leftPointsToReachNextTier) {
		this.leftPointsToReachNextTier = leftPointsToReachNextTier;
	}
	
	

}
