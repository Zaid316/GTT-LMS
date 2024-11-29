package com.hexaware.gtt.lms.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.gtt.lms.entities.Tiers;
import com.hexaware.gtt.lms.entities.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {
	Tiers getTiersByUId(UUID uId);
//	@Query("SELECT t FROM Tiers t WHERE t.uId = :uId")Tiers getTiersById(@Param("uId") UUID uId);

	
	@Query("SELECT u.uId FROM Users u WHERE u.partner.id = :partnerId AND u.userId = :userId")
	UUID findUIdByPartnerIdAndUserId(@Param("partnerId") UUID partnerId, @Param("userId") Long userId);
}
