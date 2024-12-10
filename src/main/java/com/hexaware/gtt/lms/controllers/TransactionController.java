package com.hexaware.gtt.lms.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.gtt.lms.dto.PointsAmountRequestDto;
import com.hexaware.gtt.lms.dto.PointsAmountResponseDto;
import com.hexaware.gtt.lms.dto.TransactionDto;
import com.hexaware.gtt.lms.dto.TransactionRequestDto;
import com.hexaware.gtt.lms.dto.UserCouponRequestDto;
import com.hexaware.gtt.lms.dto.UserCouponResponseDto;
import com.hexaware.gtt.lms.services.TransactionService;
@RestController
@RequestMapping("/api/v1/lms/transactions")
public class TransactionController {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private TransactionService transactionService;
	
	
	@PostMapping("/finalPrice")
	public ResponseEntity<?> finalAmount(@RequestBody PointsAmountRequestDto pointsAmountRequestDto) {
		PointsAmountResponseDto pointsAmountResponseDto=this.transactionService.finalAmount(pointsAmountRequestDto);
		return ResponseEntity.ok(pointsAmountResponseDto);
	}

	@PostMapping("/applyCoupon")
	public ResponseEntity<?> applyCoupon(@RequestBody UserCouponRequestDto userCouponRequestDto) {
		UserCouponResponseDto userCouponResponseDto=this.transactionService.applyCoupon(userCouponRequestDto);
		return ResponseEntity.ok(userCouponResponseDto);
	}
	
	@PostMapping("/newTransaction")
	public ResponseEntity<?> newTransaction(@RequestBody TransactionRequestDto transactionRequestDto){
		TransactionDto transactionDto = transactionService.createTransaction(transactionRequestDto);
		return ResponseEntity.ok(transactionRequestDto);
	}
}
