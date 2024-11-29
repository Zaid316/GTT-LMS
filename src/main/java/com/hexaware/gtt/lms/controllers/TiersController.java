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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.gtt.lms.dto.FreeTiersDto;
import com.hexaware.gtt.lms.dto.PartnerDto;
import com.hexaware.gtt.lms.dto.TiersDto;
import com.hexaware.gtt.lms.entities.Partner;
import com.hexaware.gtt.lms.entities.Tiers;
import com.hexaware.gtt.lms.services.TiersService;

@RestController
@RequestMapping("/api/v1/lms/tiers")
public class TiersController {
	
	private TiersService tiersService;
	private ModelMapper modelMapper;
	
	@Autowired
	public TiersController(TiersService tiersService, ModelMapper modelMapper) {
		super();
		this.tiersService = tiersService;
		this.modelMapper = modelMapper;
	}
	
	@PostMapping("/createTiers")
	public ResponseEntity<TiersDto> createTiers(@RequestBody TiersDto tiersDto)
	{
		Tiers tiers=this.tiersService.createTier(tiersDto);
		TiersDto tierDto=this.modelMapper.map(tiers,TiersDto.class);
		tierDto.setPartner_id(tiers.getPartner().getPartnerId());
		return ResponseEntity.ok(tierDto);
	}
	
	@PostMapping("/createFreeTiers")
	public ResponseEntity<TiersDto> createFreeTiers(@RequestBody FreeTiersDto frdto)
	{
		Tiers tiers=this.tiersService.createFreeTier(frdto);
		TiersDto tierDto=this.modelMapper.map(tiers,TiersDto.class);
		tierDto.setPartner_id(tiers.getPartner().getPartnerId());
		return ResponseEntity.ok(tierDto);
	}
	
	@DeleteMapping("/deleteTiersbyId")
	public ResponseEntity<?> deleteTiers(@RequestParam("tierId") UUID tierId)
	{
		boolean delitionStatus = this.tiersService.deleteTier(tierId);
		if(delitionStatus) {
			return ResponseEntity.ok("Successfully deleted tier");
		}
		else {
			return ResponseEntity.ok("Unsuccessfull deletion tier");
		}
		
	}
	
	@GetMapping("/getAllTiers")
	public ResponseEntity<?> getAllPartnerTiers(@RequestParam("id") UUID partner_id){
		try {
			List<Tiers> tiersLst = this.tiersService.getallTiersbyPartnerId(partner_id);
			List<TiersDto> tiersDtoList = new ArrayList<>();
			for(Tiers p: tiersLst) {
				TiersDto tdto = modelMapper.map(p, TiersDto.class);				
				tdto.setPartner_id(p.getPartner().getPartnerId());
				tiersDtoList.add(tdto);
			}
			return ResponseEntity.ok(tiersDtoList);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No tiers for this partner");
		}
		
	}
 
}