package com.hexaware.gtt.lms.services;

import java.util.List;
import java.util.UUID;

import com.hexaware.gtt.lms.dto.SuperAdminDto;
import com.hexaware.gtt.lms.entities.Partner;
import com.hexaware.gtt.lms.entities.SuperAdmin;
import com.hexaware.gtt.lms.exception.ResourceNotFoundException;

public interface SuperAdminService {

	public SuperAdmin createSuperAdmin(SuperAdminDto sprdto);
	public String statusUpdate(UUID partnerId, boolean status) throws ResourceNotFoundException;
	public List<Partner> getAllActivePartner(boolean stat);
}
