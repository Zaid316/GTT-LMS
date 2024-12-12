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

import com.hexaware.gtt.lms.dto.OffersDto;
import com.hexaware.gtt.lms.dto.OffersResponseDto;
import com.hexaware.gtt.lms.entities.Offers;
import com.hexaware.gtt.lms.exception.ResourceNotFoundException;
import com.hexaware.gtt.lms.services.OffersService;
 
@RestController
@RequestMapping("/api/v1/lms/offers")
@CrossOrigin(origins = "http://localhost:3000")
public class OffersController {
	
	private OffersService offersService;
	private ModelMapper modelMapper;
	
    @Autowired
	public OffersController(OffersService offersService, ModelMapper modelMapper) {
		super();
		this.offersService = offersService;
		this.modelMapper = modelMapper;
	}
    //http://localhost:8080/api/v1/lms/offers/createOffer
   	@PostMapping("/createOffer")
	public ResponseEntity<OffersResponseDto> createOffers(@RequestBody OffersDto offersDto) throws ResourceNotFoundException {
		Offers offers=this.offersService.createOffers(offersDto);
		OffersResponseDto offerResponseDto=this.modelMapper.map(offers,OffersResponseDto.class);
		return ResponseEntity.ok(offerResponseDto);
	}
  //http://localhost:8080/api/v1/lms/offers/getOffers
  	@GetMapping("/getOffers")
	public ResponseEntity<List<OffersResponseDto>> getOffers(){
		List<Offers> offersList= this.offersService.getOffers();
		List<OffersResponseDto> offersResponseDtoList=new ArrayList<>();
		for(Offers offer:offersList) {
			OffersResponseDto offersResponseDto=this.modelMapper.map(offer, OffersResponseDto.class);
			offersResponseDtoList.add(offersResponseDto);
		}
		return ResponseEntity.ok(offersResponseDtoList);
	}
  //http://localhost:8080/api/v1/lms/offers/getOfferById?offer_id=05fcdb70-b4f2-4308-89be-e823d87151fb
  	@GetMapping("/getOfferById")
	public ResponseEntity<OffersResponseDto> getOfferById(@RequestParam("offer_id") UUID offer_id ) throws ResourceNotFoundException {
		Offers offer = this.offersService.getOfferById(offer_id);
		OffersResponseDto offerResponseDto= this.modelMapper.map(offer,OffersResponseDto.class);
		return ResponseEntity.ok(offerResponseDto);
	}
	
	//http://localhost:8080/api/v1/lms/offers/getOffersByProgramId?program_id=e5201b21-09ed-4858-a4b6-dd63ed7f8f4e
	@GetMapping("/getOffersByProgramId")
	public ResponseEntity<List<OffersResponseDto>> getOfferByProgramId(@RequestParam("program_id") UUID programId ) throws ResourceNotFoundException {
		List<Offers> offersList = this.offersService.getOfferByProgramId(programId);
		List<OffersResponseDto> offersResponseDtoList=new ArrayList<>();
		for(Offers offer:offersList) {
			OffersResponseDto offersResponseDto=this.modelMapper.map(offer, OffersResponseDto.class);
			offersResponseDtoList.add(offersResponseDto);
		}
		return ResponseEntity.ok(offersResponseDtoList);
	}
	
	//http://localhost:8080/api/v1/lms/offers/updateOffer?offer_id=05fcdb70-b4f2-4308-89be-e823d87151fb
	@PutMapping("/updateOffer")
	public ResponseEntity<OffersResponseDto> updateOffers(@RequestBody OffersDto offersDto,@RequestParam("offer_id") UUID offer_id) throws ResourceNotFoundException {
		
		Offers offer=this.offersService.updateOffers(offersDto,offer_id);
		OffersResponseDto offerResponseDto= this.modelMapper.map(offer,OffersResponseDto.class);
		return ResponseEntity.ok(offerResponseDto);
	}
		
	//http://localhost:8080/api/v1/lms/offers/deleteOffer?offer_id=585255bd-6705-4238-be13-1fa6f36567ff
	@DeleteMapping("/deleteOffer")
	public ResponseEntity<String> deleteOffers(@RequestParam("offer_id") UUID offer_id) throws ResourceNotFoundException {
		String s = this.offersService.deleteOffers(offer_id);
		return ResponseEntity.ok(s);
	}
	
 
}
 
 