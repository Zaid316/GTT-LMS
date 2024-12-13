package com.hexaware.gtt.lms.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.gtt.lms.entities.Partner;
import com.hexaware.gtt.lms.entities.Tiers;
import com.hexaware.gtt.lms.entities.Users;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {
	@Query("SELECT t.tierId FROM Users u JOIN u.tiers t WHERE u.uId = :uId")
	UUID getTierByUId(@Param("uId") UUID uId);
	
	@Query("SELECT u.uId FROM Users u WHERE u.partner.id = :partnerId AND u.userId = :userId")
	UUID findUIdByPartnerIdAndUserId(@Param("partnerId") UUID partnerId, @Param("userId") Long userId);
	
	Users findByUId(UUID uId);

	List<Users> findAllByPartner(Partner partner);

}
