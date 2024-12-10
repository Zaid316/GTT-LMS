package com.hexaware.gtt.lms.servicesImpl;
 
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.gtt.lms.dto.CouponsDto;
import com.hexaware.gtt.lms.entities.Coupons;
import com.hexaware.gtt.lms.entities.Program;
import com.hexaware.gtt.lms.entities.Tiers;
import com.hexaware.gtt.lms.exception.ResourceNotFoundException;
import com.hexaware.gtt.lms.repositories.CouponRepository;
import com.hexaware.gtt.lms.repositories.ProgramRepository;
import com.hexaware.gtt.lms.repositories.TiersRepository;
import com.hexaware.gtt.lms.services.CouponsService;
 
 
@Service
public class CouponsServiceImpl implements CouponsService {
    private CouponRepository couponsRepository;
    private TiersRepository tiersRepository;
    private ProgramRepository programRepository;
    private ModelMapper modelMapper;
 
    @Autowired
    public CouponsServiceImpl(CouponRepository couponsRepository, ModelMapper modelMapper, TiersRepository tiersRepository, ProgramRepository programRepository) {
        super();
        this.couponsRepository = couponsRepository;
        this.modelMapper = modelMapper;
        this.tiersRepository = tiersRepository;
        this.programRepository = programRepository;
    }
 
    @Override
    public Coupons createCoupons(CouponsDto couponsDto) throws ResourceNotFoundException{
        Coupons coupons = this.modelMapper.map(couponsDto, Coupons.class); // ModelMapper line retained
        Tiers tiers = this.tiersRepository.findById(couponsDto.getTierId())
            .orElseThrow(() -> new ResourceNotFoundException("Tier", "id", couponsDto.getTierId()));
        UUID programId = couponsDto.getProgramId();
        Program program = programRepository.findById(programId).orElse(null);
        coupons.setProgram(program);
        coupons.setTiers(tiers);
        return this.couponsRepository.save(coupons);
    }
 
    @Override
    public List<Coupons> getCoupons() {
        return this.couponsRepository.findAll();
    }
 
    @Override
    public Coupons updateCoupons(CouponsDto couponsDto, UUID coupon_id) throws ResourceNotFoundException {
        Coupons coupons = this.couponsRepository.findById(coupon_id)
            .orElseThrow(() -> new ResourceNotFoundException("Coupon", "id", coupon_id));
        coupons.setCouponTitle(couponsDto.getCouponTitle());
        coupons.setMaxLimit(couponsDto.getMaxLimit());
        coupons.setPercentage(couponsDto.getPercentage());
        coupons.setCouponDescription(couponsDto.getCouponDescription());
        coupons.setStatus(couponsDto.isStatus());
        
        Tiers tiers = this.tiersRepository.findById(couponsDto.getTierId())
            .orElseThrow(() -> new ResourceNotFoundException("Tier", "id", couponsDto.getTierId()));
        
        coupons.setTiers(tiers);
        coupons.setValidity(couponsDto.getValidity());
 
        return this.couponsRepository.save(coupons);
    }
 
    @Override
    public String deleteCoupons(UUID coupon_id) throws ResourceNotFoundException {
        Coupons coupons = this.couponsRepository.findById(coupon_id)
            .orElseThrow(() -> new ResourceNotFoundException("Coupon", "id", coupon_id));
        
        this.couponsRepository.delete(coupons);
        return "Coupon " + coupon_id + " deleted successfully";
    }
 
    @Override
    public Coupons getCouponsById(UUID coupon_id) throws ResourceNotFoundException {
        return this.couponsRepository.findById(coupon_id)
            .orElseThrow(() -> new ResourceNotFoundException("Coupon", "id", coupon_id));
    }

	@Override
	public List<Coupons> getCouponsByProgramId(UUID programId) throws ResourceNotFoundException{
		Program program = programRepository.findById(programId).orElseThrow(() -> new ResourceNotFoundException("Program", "id", programId));
		return this.couponsRepository.findAllOffersByProgram(program);
		
	}
}
 
 