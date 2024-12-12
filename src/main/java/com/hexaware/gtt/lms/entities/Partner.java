package com.hexaware.gtt.lms.entities;
 
import java.time.LocalDateTime;
import java.util.UUID;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
 
@Entity
public class Partner {
 
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID partnerId;
 
    private String partnerName;
    private String email;
    private String password;
 
    @Column(updatable = false)
    private final LocalDateTime dateJoined = LocalDateTime.now();
 
    private boolean status;
    
    private boolean isNewPartner;
    
    private int countryCode;
    
    @Column(unique=true)
    private Long contact;//have to add country code and should be unique
 
    public int getCountryCode() {
		return countryCode;
	}
 
	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}
 
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

	public boolean isNewPartner() {
		return isNewPartner;
	}

	public void setNewPartner(boolean isNewPartner) {
		this.isNewPartner = isNewPartner;
	}
 
}