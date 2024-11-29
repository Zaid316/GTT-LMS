package com.hexaware.gtt.lms.servicesImpl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.gtt.lms.dto.PartnerDto;
import com.hexaware.gtt.lms.entities.Partner;
import com.hexaware.gtt.lms.repositories.PartnerRepository;
import com.hexaware.gtt.lms.services.PartnerService;

@Service
public class PartnerServiceImpl implements PartnerService {
	
	@Autowired
	private PartnerRepository partnerRepository;
	
	@Autowired
	private ModelMapper modelmapper;
	
//	public Partner findByEmail(String email)throws Exception
//	{
//		Partner part=this.partnerRepository.findByEmail(email);
//		if(part==null)
//		{
//			throw new Exception();
//		}
//		return part;
//	}
	
	public Partner createPartner(PartnerDto ptrdto)throws Exception
	{
		if(partnerRepository.findByEmail(ptrdto.getEmail())!=null) {
			System.out.println("35");
			throw new Exception();
		}
		else {
			System.out.println("else condition");
			Partner partner=modelmapper.map(ptrdto, Partner.class);
			Partner part = partnerRepository.save(partner); 
			return part;
		}	
	}

	@Override
	public Partner getPartnerById(UUID id) throws Exception {
		 
		 if(id==null)
		 {
			 throw new Exception();
		 }
		 Partner part=partnerRepository.findById(id).get();
		return part;
	}

	@Override
	public long getPartnerCount() {
		 Long partcount=partnerRepository.count();
		return partcount;
	}

	@Override
	public List<Partner> getAllPartners() throws Exception {
		List<Partner> ptlist=partnerRepository.findAll();
		if(ptlist==null) {
			throw new Exception();
		}
		else {
			return ptlist;
		}
	}

	@Override
	public Partner updatePartner(String email, PartnerDto partnerDto) throws Exception {
		Partner part=this.partnerRepository.findByEmail(email);
		if(part==null)
		{
			throw new Exception();
		}
		else {
			part.setPartnerName(partnerDto.getPartnerName());
			part.setEmail(partnerDto.getEmail());
			part.setContact(partnerDto.getContact());
			Partner savedPartner = partnerRepository.save(part);
			return savedPartner;
			
		}
	}

	@Override
	public boolean deletePartner(UUID id) throws Exception {
		Partner part=partnerRepository.findById(id).get();
		if(part.equals(null))
		{
			throw new Exception();
			
		}
		partnerRepository.deleteById(id);		
		if(partnerRepository.existsById(id))
			{
			return false;
			}
		else 
		{
			return true;
		}
	}

}
