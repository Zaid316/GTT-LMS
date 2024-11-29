package com.hexaware.gtt.lms.controllers;
 
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.gtt.lms.dto.CouponGenerationDto;
import com.hexaware.gtt.lms.dto.UserCouponDto;
import com.hexaware.gtt.lms.dto.UserPartnerDto;
import com.hexaware.gtt.lms.entities.UserCoupons;
import com.hexaware.gtt.lms.services.UserCouponService;

@RestController

@RequestMapping("/lms/api/v1/usercoupons/")

@ResponseBody

public class CouponController {
 
	@Autowired

	private ModelMapper modelMapper;

	@Autowired
	private UserCouponService userCouponService;
	
	@Autowired
	public CouponController(UserCouponService userCouponService) {
		
		this.userCouponService = userCouponService;
	}

	@PostMapping("generateCoupon")

	public ResponseEntity<UserCouponDto> generateCoupon(@RequestBody UserPartnerDto userPartnerDto){

		UUID uId=userCouponService.finduid(userPartnerDto);

		UUID tierId=userCouponService.findTierIdbyUId(uId);

		float couponProbability=userCouponService.findProbablity(tierId);

		UUID couponId=userCouponService.getCouponId(tierId);

		UserCouponDto userCouponDto;

		if(userCouponService.awardCoupon(couponProbability)) {

			CouponGenerationDto couponGenerationDto = new CouponGenerationDto(couponId,uId);

			UserCoupons userCoupons =userCouponService.generateCoupon(couponGenerationDto);

			userCouponDto=modelMapper.map(userCoupons, UserCouponDto.class);

		}

		else {

			System.out.println("Better luck");

			userCouponDto = new UserCouponDto();

		}

		return ResponseEntity.ok(userCouponDto);

	}

	

}

 
