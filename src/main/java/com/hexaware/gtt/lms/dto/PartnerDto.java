package com.hexaware.gtt.lms.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class PartnerDto {

    private UUID partnerId;
    
    @NotNull
    private String partnerName;
    
    @Email 
    @NotNull
    private String email;
    
    @NotNull
    private String password;

    
    private final LocalDateTime dateJoined = LocalDateTime.now();

    private boolean status;
    
    @NotNull(message = "phone number should not be empty")
    private Long contact;
    


    public Long getContact() {
		return contact;
	}

	public void setContact(Long cont) {
		contact = cont;
	}


    public UUID getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(UUID partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getDateJoined() {
        return dateJoined;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
