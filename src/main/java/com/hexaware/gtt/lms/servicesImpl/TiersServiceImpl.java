package com.hexaware.gtt.lms.servicesImpl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.gtt.lms.dto.FreeTiersDto;
import com.hexaware.gtt.lms.dto.TiersDto;
import com.hexaware.gtt.lms.entities.Partner;
import com.hexaware.gtt.lms.entities.Tiers;
import com.hexaware.gtt.lms.repositories.PartnerRepository;
import com.hexaware.gtt.lms.repositories.TiersRepository;
import com.hexaware.gtt.lms.services.TiersService;

@Service
public class TiersServiceImpl implements TiersService {
	
	private ModelMapper modelMapper;
	private TiersRepository tiersRepository;
	private PartnerRepository partnerRepository;
	
	@Autowired
	public TiersServiceImpl(ModelMapper modelMapper, TiersRepository tiersRepository, PartnerRepository partnerRepository) {
		super();
		this.modelMapper = modelMapper;
		this.tiersRepository = tiersRepository;
		this.partnerRepository=partnerRepository;
	}
 
//	@Override
//	public Tiers createTiers(TiersDto tiersDto) {
//		
//		Tiers tiers = this.modelMapper.map(tiersDto,Tiers.class);
//		this.tiersRepository.save(tiers);
//		return tiers;
//	}
	
	@Override
	public  Tiers createTier(TiersDto ti)
	{
		Tiers tir=modelMapper.map(ti,Tiers.class );
		Partner part=this.partnerRepository.findById(ti.getPartner_id()).get();
		tir.setPartner(part);
		Tiers savedtir=this.tiersRepository.save(tir);
		return savedtir;
				
	}
	
	@Override
	public Tiers createFreeTier(FreeTiersDto frdto)
	{
		Tiers tir=modelMapper.map(frdto,Tiers.class );
		Partner part=this.partnerRepository.findById(frdto.getPartner_id()).get();
		tir.setPartner(part);
		Tiers savedtir=this.tiersRepository.save(tir);
		return savedtir;
	}
	
	@Override
	public boolean deleteTier(UUID id)
	{
		
		if(tiersRepository.findById(id).get()!=null)
		{
			this.tiersRepository.deleteById(id);
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public List<Tiers> getallTiersbyPartnerId(UUID id) throws Exception
	{
		Partner part=this.partnerRepository.findById(id).get();
		List<Tiers> tierlst=this.tiersRepository.getByPartner(part);
		if(tierlst.isEmpty()) {
			throw new Exception();
		}
		else {
			return tierlst;
		}
	}
	
	
	
	
}
