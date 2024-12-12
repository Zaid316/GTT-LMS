package com.hexaware.gtt.lms.servicesImpl;
 
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.gtt.lms.dto.OffersDto;
import com.hexaware.gtt.lms.entities.Offers;
import com.hexaware.gtt.lms.entities.Program;
import com.hexaware.gtt.lms.entities.Tiers;
import com.hexaware.gtt.lms.exception.ResourceNotFoundException;
import com.hexaware.gtt.lms.repositories.OffersRepository;
import com.hexaware.gtt.lms.repositories.ProgramRepository;
import com.hexaware.gtt.lms.repositories.TiersRepository;
import com.hexaware.gtt.lms.services.OffersService;

@Service
public class OffersServiceImpl implements OffersService {
    private OffersRepository offersRepository;
    @Autowired
    private TiersRepository tiersRepository;
    @Autowired
    private ProgramRepository programRepository;
    
    private ModelMapper modelMapper;

    @Autowired
    public OffersServiceImpl(OffersRepository offersRepository, ModelMapper modelMapper) {
        super();
        this.offersRepository = offersRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Offers> getOffers() {
        return offersRepository.findAll();
    }

    @Override
    public Offers createOffers(OffersDto offersDto) throws ResourceNotFoundException {
        Offers offers = this.modelMapper.map(offersDto, Offers.class);
        Tiers tiers = this.tiersRepository.findById(offersDto.getTierId())
            .orElseThrow(() -> new ResourceNotFoundException("Tier", "id", offersDto.getTierId()));
        System.out.println("program id: "+ offersDto.getProgramId());
        Program program =programRepository.findById(offersDto.getProgramId()).orElse(null);
        offers.setProgram(program);
        offers.setTiers(tiers);
        return this.offersRepository.save(offers);
    }

    @Override
    public Offers updateOffers(OffersDto offersDto, UUID offer_id) throws ResourceNotFoundException {
        Offers offers = this.modelMapper.map(offersDto, Offers.class);
        Offers offer = this.offersRepository.findById(offer_id)
            .orElseThrow(() -> new ResourceNotFoundException("Offer", "id", offer_id));

        Tiers tiers = this.tiersRepository.findById(offersDto.getTierId())
            .orElseThrow(() -> new ResourceNotFoundException("Tier", "id", offersDto.getTierId()));

        // Update offer fields
        offer.setImageUrl(offers.getImageUrl());
        offer.setBenefit(offers.getBenefit());
        offer.setOfferDescription(offers.getOfferDescription());
        offer.setOfferTitle(offers.getOfferTitle());
        offer.setTiers(tiers);
        offer.setStatus(offers.isStatus());

        return this.offersRepository.save(offer);
    }

    @Override
    public String deleteOffers(UUID offer_id) throws ResourceNotFoundException {
        Offers offer = this.offersRepository.findById(offer_id)
            .orElseThrow(() -> new ResourceNotFoundException("Offer", "id", offer_id));

        this.offersRepository.delete(offer);
        return "Offer " + offer_id + " deleted successfully";
    }

    @Override
    public Offers getOfferById(UUID offer_id) throws ResourceNotFoundException {
        return this.offersRepository.findById(offer_id)
            .orElseThrow(() -> new ResourceNotFoundException("Offer", "id", offer_id));
    }
    
    @Override
    public List<Offers> getOfferByProgramId(UUID programId) throws ResourceNotFoundException {
    	Program program = programRepository.findById(programId).orElseThrow(() -> new ResourceNotFoundException("Program", "id", programId));
        return this.offersRepository.findAllOffersByProgram(program);
    }

	@Override
	public Offers getOfferByProgramIdAndTierId(UUID programId, UUID tierId) {
		List<Offers> offersList=  offersRepository.findOffersByProgramAndTier(programId, tierId);
		return offersList.get(0);
	}
}
 