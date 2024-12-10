package com.hexaware.gtt.lms.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.gtt.lms.entities.Partner;
@Repository
public interface PartnerRepository extends JpaRepository<Partner, UUID> {

	public Partner findByEmail(String email);

	public Partner findByPartnerId(UUID partnerId);


	public Object findByContact(Long contact);
	
	public List<Partner> findPartnerByStatus(boolean stat);
	
	
}
