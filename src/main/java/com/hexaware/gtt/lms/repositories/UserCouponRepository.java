package com.hexaware.gtt.lms.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.gtt.lms.entities.UserCoupons;

@Repository
public interface UserCouponRepository extends JpaRepository<UserCoupons, String> {

	UserCoupons findCouponByCouponCode(String couponCode);

	boolean existsCouponByCouponCode(String couponCode);

	List<UserCoupons> findCouponByUsers_UId(UUID userId);

}