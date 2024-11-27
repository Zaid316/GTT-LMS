package com.hexaware.gtt.lms.services;

import com.hexaware.gtt.lms.dto.CouponGenerationDto;
import com.hexaware.gtt.lms.entities.UserCoupons;

public interface UserCouponService {
	  public UserCoupons generateCoupon(CouponGenerationDto couponGenerationDto);
	 public boolean redeemCoupon(String couponCode);
	 
}
