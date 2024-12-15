package com.hexaware.gtt.lms.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hexaware.gtt.lms.entities.Coupons;
import com.hexaware.gtt.lms.entities.Offers;

public class ProgramDto {
	private UUID programId;
	private String programName;
	 private UUID partnerId;
	private boolean status;
	private boolean defaultProgram;
	private List<Coupons> coupons;
	private List<Offers> offers;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	private LocalDateTime startDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	private LocalDateTime endDate;
	public ProgramDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	


	public UUID getProgramId() {
		return programId;
	}


	public void setProgramId(UUID programId) {
		this.programId = programId;
	}


	public String getProgramName() {
		return programName;
	}


	public void setProgramName(String programName) {
		this.programName = programName;
	}


	public UUID getPartnerId() {
		return partnerId;
	}


	public void setPartnerId(UUID partnerId) {
		this.partnerId = partnerId;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public List<Coupons> getCoupons() {
		return coupons;
	}


	public void setCoupons(List<Coupons> coupons) {
		this.coupons = coupons;
	}


	public List<Offers> getOffers() {
		return offers;
	}


	public void setOffers(List<Offers> offers) {
		this.offers = offers;
	}


	public LocalDateTime getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}


	public LocalDateTime getEndDate() {
		return endDate;
	}


	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public boolean isDefaultProgram() {
		return defaultProgram;
	}





	public void setDefaultProgram(boolean defaultProgram) {
		this.defaultProgram = defaultProgram;
	}
	public ProgramDto(UUID programId, String programName, UUID partnerId, boolean status, boolean defaultProgram,
			List<Coupons> coupons, List<Offers> offers, LocalDateTime startDate, LocalDateTime endDate) {
		super();
		this.programId = programId;
		this.programName = programName;
		this.partnerId = partnerId;
		this.status = status;
		this.defaultProgram = defaultProgram;
		this.coupons = coupons;
		this.offers = offers;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "ProgramDto [programId=" + programId + ", programName=" + programName + ", partnerId=" + partnerId
				+ ", status=" + status + ", defaultProgram=" + defaultProgram + ", coupons=" + coupons + ", offers="
				+ offers + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
}
