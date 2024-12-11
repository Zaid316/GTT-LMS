package com.hexaware.gtt.lms.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.hexaware.gtt.lms.exception.ResourceNotFoundException;
import com.hexaware.gtt.lms.services.TiersService;

@RestController
@RequestMapping("/api/v1/lms/tiers")
@CrossOrigin(origins = "http://localhost:3000")
public class TiersController {
	
	private TiersService tiersService;
	private ModelMapper modelMapper;
	
	@Autowired
	public TiersController(TiersService tiersService, ModelMapper modelMapper) {
		super();
		this.tiersService = tiersService;
		this.modelMapper = modelMapper;
	}
	
	//http://localhost:8080/api/v1/lms/tiers/createTier
	@PostMapping("/createTier")
	public ResponseEntity<TiersDto> createTiers(@RequestBody TiersDto tiersDto)
	{
		Tiers tiers=this.tiersService.createTier(tiersDto);
		TiersDto tierDto=this.modelMapper.map(tiers,TiersDto.class);
		tierDto.setPartner_id(tiers.getPartner().getPartnerId());
		return ResponseEntity.ok(tierDto);
	}
	
	//http://localhost:8080/api/v1/lms/tiers/createFreeTier
	@PostMapping("/createFreeTier")
	public ResponseEntity<TiersDto> createFreeTiers(@RequestBody FreeTiersDto frdto)
	{
		Tiers tiers=this.tiersService.createFreeTier(frdto);
		TiersDto tierDto=this.modelMapper.map(tiers,TiersDto.class);
		tierDto.setPartner_id(tiers.getPartner().getPartnerId());
		return ResponseEntity.ok(tierDto);
	}
	
	//http://localhost:8080/api/v1/lms/tiers/deleteTierbyId?tierId=c8682ca3-c767-404c-8057-9de6c48747fb
	@DeleteMapping("/deleteTierbyId")
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
	
	//http://localhost:8080/api/v1/lms/tiers/getAllPartnerTiers?id=0100761c-0755-4abd-ba10-b57ba721a351
	@GetMapping("/getAllPartnerTiers")
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
	
	//http://localhost:8080/api/v1/lms/tiers/getTierByTierId?id=7d14ad27-e639-4a9f-80ba-bbcdd31b2ae5
	@GetMapping("/getTierByTierId")
	public ResponseEntity<?> getTierbyTierId(@RequestParam("id")UUID id) throws Exception
	{
		try {
			Tiers tier=this.tiersService.findTierbyTierId(id);
			TiersDto tierdto=this.modelMapper.map(tier, TiersDto.class);
			tierdto.setPartner_id(tier.getPartner().getPartnerId());
			return ResponseEntity.ok(tierdto);
			
		}
		
		catch(ResourceNotFoundException e)
		{
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("The tier is not available");
		}
		catch(Exception e)
		{
			return ResponseEntity.ok(e.getMessage());
		}
	}
 
}