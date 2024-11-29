package com.hexaware.gtt.lms.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.gtt.lms.entities.Partner;
import com.hexaware.gtt.lms.entities.Tiers;

@Repository
public interface TiersRepository extends JpaRepository<Tiers, UUID> {
	
	public List<Tiers> getByPartner(Partner partner);
	float getCouponProbablityByTierId(UUID TierId);

}
