package com.hexaware.gtt.lms.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class ProgramRequestDto {
	private String programName;
	 private UUID partnerId;
	private boolean status;
	private LocalDateTime startDate;
	private LocalDateTime endDate;

	public ProgramRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProgramRequestDto(String programName, UUID partnerId, boolean status, LocalDateTime startDate,
			LocalDateTime endDate) {
		super();
		this.programName = programName;
		this.partnerId = partnerId;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
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

	@Override
	public String toString() {
		return "ProgramRequestDto [programName=" + programName + ", partnerId=" + partnerId + ", status=" + status
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

	

}
