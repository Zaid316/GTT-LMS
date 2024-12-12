package com.hexaware.gtt.lms.services;
 
import java.util.List;
import java.util.UUID;

import com.hexaware.gtt.lms.dto.Logindto;
import com.hexaware.gtt.lms.dto.PartnerDto;
import com.hexaware.gtt.lms.entities.Partner;
import com.hexaware.gtt.lms.exception.DuplicateDataException;
import com.hexaware.gtt.lms.exception.ResourceDeletionException;
import com.hexaware.gtt.lms.exception.ResourceNotFoundException;
 
 
public interface PartnerService {
	public Partner createPartner(PartnerDto ptrdto) throws DuplicateDataException;
	Partner getPartnerById(UUID id) throws ResourceNotFoundException;
 
	long getPartnerCount();
 
	List<Partner> getAllPartners() throws ResourceNotFoundException;
 
	Partner updatePartner(String email, PartnerDto partnerDto) throws ResourceNotFoundException;
 
	boolean deletePartner(UUID id) throws ResourceDeletionException;
	
	public Logindto loginPartner(String email,String pwd);
	
	public List<Partner> getApprovedPartners();
	
	public List<Partner> getRejectedPartners();
	
	public List<Partner> getNewPartners();
}