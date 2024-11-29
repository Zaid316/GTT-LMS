package com.hexaware.gtt.lms.services;

import java.util.UUID;

import com.hexaware.gtt.lms.dto.CouponGenerationDto;
import com.hexaware.gtt.lms.entities.UserCoupons;

public interface UserCouponService {
	  public UserCoupons generateCoupon(CouponGenerationDto couponGenerationDto);
	  public String generateRandomCouponCode(int length);
	  public String redeemCoupon(String couponCode, UUID user_id);
}
