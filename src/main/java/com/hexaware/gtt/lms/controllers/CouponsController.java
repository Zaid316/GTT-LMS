package com.hexaware.gtt.lms.controllers;
 
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.gtt.lms.dto.CouponsDto;
import com.hexaware.gtt.lms.dto.CouponsResponseDto;
import com.hexaware.gtt.lms.entities.Coupons;
import com.hexaware.gtt.lms.exception.ResourceNotFoundException;
import com.hexaware.gtt.lms.services.CouponsService;
 
@RestController
@RequestMapping("/api/v1/lms/coupons")
@CrossOrigin(origins = "http://localhost:3000")
public class CouponsController {
	
	private CouponsService couponsService;
	private ModelMapper modelMapper;
	
	
	@Autowired
	public CouponsController(CouponsService couponsService, ModelMapper modelMapper) {
		super();
		this.couponsService = couponsService;
		this.modelMapper = modelMapper;
	}
	
	//http://localhost:8080/api/v1/lms/coupons/createCoupon
	@PostMapping("/createCoupon")
	public ResponseEntity<CouponsResponseDto> createCoupons(@RequestBody CouponsDto couponsDto) throws ResourceNotFoundException {
		Coupons coupons=this.couponsService.createCoupons(couponsDto);
		CouponsResponseDto couponResponseDto=this.modelMapper.map(coupons,CouponsResponseDto.class);
		couponResponseDto.setTierId(coupons.getTiers().getTierId());
		
		return ResponseEntity.ok(couponResponseDto);
	}
	
	//http://localhost:8080/api/v1/lms/coupons/getCoupons
	@GetMapping("/getCoupons")
	public ResponseEntity<List<CouponsResponseDto>> getCoupons(){
		List<Coupons> couponsList=this.couponsService.getCoupons();
		List<CouponsResponseDto> couponsResponseDtoList=new ArrayList<>();
		for(Coupons coupon : couponsList) {
			CouponsResponseDto couponsResponseDto=this.modelMapper.map(coupon, CouponsResponseDto.class);
			couponsResponseDto.setTierId(coupon.getTiers().getTierId());
			couponsResponseDtoList.add(couponsResponseDto);
			
		}
		return ResponseEntity.ok(couponsResponseDtoList);
		
		
	}
	
	//http://localhost:8080/api/v1/lms/coupons/getCouponById?coupon_id=3b6b1020-2004-4118-906a-52566fcfc27d
	@GetMapping("/getCouponById")
	public ResponseEntity<CouponsResponseDto> getCouponsById(@RequestParam("coupon_id") UUID coupon_id) throws ResourceNotFoundException{
		Coupons coupon = this.couponsService.getCouponsById(coupon_id);
		CouponsResponseDto couponsResponseDto=this.modelMapper.map(coupon, CouponsResponseDto.class);
		couponsResponseDto.setTierId(coupon.getTiers().getTierId());
		return ResponseEntity.ok(couponsResponseDto);
	}
	
	
	//http://localhost:8080/api/v1/lms/coupons/getCouponByProgramId?program_id=e5201b21-09ed-4858-a4b6-dd63ed7f8f4e
	@GetMapping("/getCouponByProgramId")
	public ResponseEntity<List<CouponsResponseDto>> getCouponsByProgramId(@RequestParam("program_id") UUID programId) throws ResourceNotFoundException{
		List<Coupons> couponsList = this.couponsService.getCouponsByProgramId(programId);
		List<CouponsResponseDto> couponsResponseDtoList=new ArrayList<>();
		for(Coupons coupon : couponsList) {
			CouponsResponseDto couponsResponseDto=this.modelMapper.map(coupon, CouponsResponseDto.class);
			couponsResponseDto.setTierId(coupon.getTiers().getTierId());
			couponsResponseDtoList.add(couponsResponseDto);
			
		}
		return ResponseEntity.ok(couponsResponseDtoList);
	}
	
	@GetMapping("/getStandaloneCouponsByPartner")
	public ResponseEntity<List<CouponsResponseDto>> getStandaloneCouponsByPartner(@RequestParam("partner_id") UUID partnerId) throws ResourceNotFoundException{
		List<Coupons> couponsList = this.couponsService.getStandaloneCoupons(partnerId);
		List<CouponsResponseDto> couponsResponseDtoList=new ArrayList<>();
		for(Coupons coupon : couponsList) {
			CouponsResponseDto couponsResponseDto=this.modelMapper.map(coupon, CouponsResponseDto.class);
			couponsResponseDto.setTierId(coupon.getTiers().getTierId());
			couponsResponseDtoList.add(couponsResponseDto);
			
		}
		return ResponseEntity.ok(couponsResponseDtoList);
	}
	
	//http://localhost:8080/api/v1/lms/coupons/updateCoupon?coupon_id=3b6b1020-2004-4118-906a-52566fcfc27d
	@PutMapping("/updateCoupon")
	public ResponseEntity<CouponsResponseDto> updateCoupons(@RequestBody CouponsDto couponsDto,@RequestParam("coupon_id") UUID coupon_id) throws ResourceNotFoundException {
		Coupons coupon=this.couponsService.updateCoupons(couponsDto,coupon_id);
		CouponsResponseDto couponResponseDto=this.modelMapper.map(coupon, CouponsResponseDto.class);
		return ResponseEntity.ok(couponResponseDto);
	}
	
	//http://localhost:8080/api/v1/lms/coupons/deleteCoupon?coupon_id=38ec2ce7-8fe0-4962-a535-a90b9f7485eb
	@DeleteMapping("/deleteCoupon")
	public ResponseEntity<String> deleteCoupons(@RequestParam("coupon_id") UUID coupon_id) throws ResourceNotFoundException{
		String s = this.couponsService.deleteCoupons(coupon_id);
		return ResponseEntity.ok(s);
	}
	
	
	
}
 