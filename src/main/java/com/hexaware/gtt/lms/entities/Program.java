package com.hexaware.gtt.lms.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Program {
	@Id
	@GeneratedValue
	private UUID programId;
	private String programName;
	private boolean status;
	private boolean isDefault;

	@ElementCollection
//	@CollectionTable(name = "program_coupons", joinColumns = @JoinColumn(name = "program_id"))
	@OneToMany(mappedBy = "program", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Coupons> coupons;
//	@CollectionTable(name = "program_offers", joinColumns = @JoinColumn(name = "program_id"))
	@OneToMany(mappedBy = "program", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Offers> offers;

	@ManyToOne
	@JoinColumn(name = "partner_id", nullable = false, updatable = false)
	private Partner partner;
	
	private LocalDateTime startDate;
	private LocalDateTime endDate;

	public Program() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Program(UUID programId, String programName, boolean status, List<Coupons> coupons, List<Offers> offers,
			Partner partner, LocalDateTime startDate, LocalDateTime endDate,  boolean isDefault) {
		super();
		this.programId = programId;
		this.programName = programName;
		this.status = status;
		this.coupons = coupons;
		this.offers = offers;
		this.partner = partner;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isDefault = isDefault;
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



	public Partner getPartner() {
		return partner;
	}



	public void setPartner(Partner partner) {
		this.partner = partner;
	}



	public boolean isDefault() {
		return isDefault;
	}



	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}



	@Override
	public String toString() {
		return "Program [programId=" + programId + ", programName=" + programName + ", status=" + status
				+ ", isDefault=" + isDefault + ", coupons=" + coupons + ", offers=" + offers + ", partner=" + partner
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}



	
	

}
