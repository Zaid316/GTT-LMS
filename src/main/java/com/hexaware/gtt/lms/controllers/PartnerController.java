package com.hexaware.gtt.lms.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.gtt.lms.dto.PartnerDto;
import com.hexaware.gtt.lms.entities.Partner;
import com.hexaware.gtt.lms.services.PartnerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/lms/api/v1/partner/")
@ResponseBody
public class PartnerController {
	
	private PartnerService partnerService;
	private ModelMapper modelmapper;
	
	@Autowired
	public PartnerController(PartnerService partnerService, ModelMapper modelmapper) {
		this.partnerService = partnerService;
		this.modelmapper = modelmapper;
	}	
	
	@GetMapping("getPartnerById/")
	public ResponseEntity<?> getbyId(@RequestParam("id") UUID id)
	{
		try{
			Partner part=partnerService.getPartnerById(id);
			PartnerDto partDto=modelmapper.map(part, PartnerDto.class);
			return ResponseEntity.ok(partDto);
		}catch(Exception e){
			
			return ResponseEntity.ok("partner Id is not Exist");
		}		
	}
	
	@PostMapping("register/")
	public ResponseEntity<?> createPartner(@Valid @RequestBody PartnerDto prtDto) 
	{
		try {
			System.out.println("entered controller try");
			Partner part;
			part = partnerService.createPartner(prtDto);
			System.out.println("came out of service");
			PartnerDto partDto=modelmapper.map(part,PartnerDto.class);
			return ResponseEntity.ok(partDto);
		} catch (Exception e) {
				return ResponseEntity.ok("partner is already Exist");
		}
				
	}
	
	@GetMapping("partnercount/")
	public ResponseEntity<?> getPartnerCount(){
		long partnerCount = partnerService.getPartnerCount();
		return ResponseEntity.ok(partnerCount);
	}
	
	@GetMapping("getAllPartners/")
	public ResponseEntity<?> getAllPartners(){
		try {
			List<Partner> partList = partnerService.getAllPartners();
			List<PartnerDto> partDtoList = new ArrayList<>();
			for(Partner p: partList) {
				partDtoList.add(modelmapper.map(p, PartnerDto.class));
			}
			return ResponseEntity.ok(partDtoList);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No partners registered yet");
		}
	}
	
	@PutMapping("updatePartner/")
	public ResponseEntity<?> updatePartner(@RequestParam("email") String email, @Valid @RequestBody PartnerDto partnerDto){
		try {
			Partner partner = partnerService.updatePartner(email, partnerDto);
			PartnerDto partDto = modelmapper.map(partner, PartnerDto.class);
			return ResponseEntity.ok(partDto);
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Partner not existed with the given email");
		}
	}
	
	@DeleteMapping("deletePartnerById/")
	public ResponseEntity<?> deletePartner(@RequestParam("id") UUID id){
		try {
			boolean status = partnerService.deletePartner(id);
			if(status) {
				return ResponseEntity.ok("Successfully deleted partner");
			}
			else {
				return ResponseEntity.ok("Unsuccessfull deletion attempt");
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Partner not existed with the given id");
		}
	}
	
	
}
