package com.hexaware.gtt.lms.services;

import java.util.List;
import java.util.UUID;

import com.hexaware.gtt.lms.dto.CouponGenerationDto;
import com.hexaware.gtt.lms.dto.UserPartnerDto;
import com.hexaware.gtt.lms.entities.Tiers;
import com.hexaware.gtt.lms.entities.UserCoupons;

public interface UserCouponService {
	  public UserCoupons generateCoupon(CouponGenerationDto couponGenerationDto);
	  public String generateRandomCouponCode(int length);
	  public String redeemCoupon(String couponCode, UUID user_id);
	  public UUID finduid(UserPartnerDto userPartnerDto);
	  public UUID findTierbyUId(UUID u_Id);
	  public float findProbablity(UUID tier_id) ;
	  public boolean awardCoupon(float couponProbability);
	  public UUID getCouponId(UUID tierId);
	  public List<UUID> fetchCouponIdsForTier(UUID tierId);
}
