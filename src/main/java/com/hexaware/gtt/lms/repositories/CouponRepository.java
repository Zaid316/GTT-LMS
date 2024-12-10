package com.hexaware.gtt.lms.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.gtt.lms.entities.Coupons;
import com.hexaware.gtt.lms.entities.Program;
@Repository
public interface CouponRepository extends JpaRepository<Coupons, UUID>{
	@Query(value = "SELECT coupon_id FROM coupons WHERE tier_id =:tierId", nativeQuery = true)
    List<UUID> findCouponsByTierId(@Param("tierId") UUID tierId);




	List<Coupons> findAllOffersByProgram(Program program);
}