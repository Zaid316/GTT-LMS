package com.hexaware.gtt.lms.services;

import java.util.List;
import java.util.UUID;

import com.hexaware.gtt.lms.dto.PartnerDto;
import com.hexaware.gtt.lms.entities.Partner;


public interface PartnerService {
	
	public Partner createPartner(PartnerDto ptrdto) throws Exception;
	
	Partner getPartnerById(UUID id) throws Exception;

	long getPartnerCount();

	List<Partner> getAllPartners() throws Exception;

	Partner updatePartner(String email, PartnerDto partnerDto) throws Exception;

	boolean deletePartner(UUID id) throws Exception;
}
