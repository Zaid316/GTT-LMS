package com.hexaware.gtt.lms.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProgramRequestDto {
	private String programName;
	 private UUID partnerId;
	private boolean status;
	private boolean defaultProgram;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	private LocalDateTime startDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	private LocalDateTime endDate;

	public ProgramRequestDto() {
		super();
		// TODO Auto-generated constructor stub
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



	public boolean isDefaultProgram() {
		return defaultProgram;
	}



	public void setDefaultProgram(boolean defaultProgram) {
		this.defaultProgram = defaultProgram;
	}



	public ProgramRequestDto(String programName, UUID partnerId, boolean status, boolean defaultProgram,
			LocalDateTime startDate, LocalDateTime endDate) {
		super();
		this.programName = programName;
		this.partnerId = partnerId;
		this.status = status;
		this.defaultProgram = defaultProgram;
		this.startDate = startDate;
		this.endDate = endDate;
	}



	@Override
	public String toString() {
		return "ProgramRequestDto [programName=" + programName + ", partnerId=" + partnerId + ", status=" + status
				+ ", defaultProgram=" + defaultProgram + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

	

}
