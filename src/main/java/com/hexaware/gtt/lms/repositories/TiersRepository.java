package com.hexaware.gtt.lms.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.gtt.lms.entities.Partner;
import com.hexaware.gtt.lms.entities.Tiers;

@Repository
public interface TiersRepository extends JpaRepository<Tiers, UUID> {
	
	public List<Tiers> getByPartner(Partner partner);
	@Query("SELECT t.couponProbability FROM Tiers t WHERE t.tierId = :tierId")
    float getCouponProbablityByTierId(@Param("tierId") UUID tierId);

	List<Tiers> findByPartner(Partner partner);

	@Query("SELECT t FROM Tiers t WHERE t.triggerAmount > :triggerAmount ORDER BY t.triggerAmount ASC")
	Tiers findNextTierByTriggerAmount(@Param("triggerAmount") Double triggerAmount);
}
