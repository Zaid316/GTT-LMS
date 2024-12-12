package com.hexaware.gtt.lms.services;

import java.util.List;
import java.util.UUID;

import com.hexaware.gtt.lms.dto.CouponGenerationDto;
import com.hexaware.gtt.lms.dto.UserPartnerDto;
import com.hexaware.gtt.lms.dto.UserValidationDto;
import com.hexaware.gtt.lms.entities.UserCoupons;

public interface UserCouponService {
	  public UserCoupons generateCoupon(CouponGenerationDto couponGenerationDto);
	  public String generateRandomCouponCode(int length);
	  public boolean redeemCoupon(UserValidationDto userValidationDto);
	  public UUID finduid(UserPartnerDto userPartnerDto);
	  public UUID findTierbyUId(UUID u_Id);
	  public float findProbablity(UUID tier_id) ;
	  public boolean awardCoupon(float couponProbability);
	  public UUID getCouponId(UUID tierId);
	  public List<UUID> fetchCouponIdsForTier(UUID tierId);
	  public boolean validateCoupon(UserValidationDto userValidationDto);
	  public List<UserCoupons> listOfActiveCoupons(UUID uId);
	  public List<UserCoupons> listOfAllCoupons(UUID uId);
	  public UUID finduidbyuserAndPartner(Long userId,UUID PartnerId);
	  
}
