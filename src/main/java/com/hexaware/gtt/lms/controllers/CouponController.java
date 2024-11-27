package com.hexaware.gtt.lms.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.hexaware.gtt.lms.servicesImpl.UserCouponServiceImpl;

public class CouponController {

	@Autowired
	private ModelMapper modelMapper;
	private UserCouponServiceImpl userCouponServiceImpl;
	
}
