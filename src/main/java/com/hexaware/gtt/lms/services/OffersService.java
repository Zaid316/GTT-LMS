package com.hexaware.gtt.lms.services;
 
import java.util.List;
import java.util.UUID;
 
import com.hexaware.gtt.lms.dto.OffersDto;
import com.hexaware.gtt.lms.entities.Offers;
import com.hexaware.gtt.lms.exception.ResourceNotFoundException;
 
public interface OffersService {
	public Offers createOffers(OffersDto offersDto) throws ResourceNotFoundException;
	public List<Offers> getOffers();
	public Offers updateOffers(OffersDto offersDto,UUID offer_id)throws ResourceNotFoundException;
	public String deleteOffers(UUID offer_id)throws ResourceNotFoundException;
	public Offers getOfferById(UUID offer_id)throws ResourceNotFoundException;
	 public  List<Offers> getOfferByProgramId(UUID programId) throws ResourceNotFoundException;
}
 
 