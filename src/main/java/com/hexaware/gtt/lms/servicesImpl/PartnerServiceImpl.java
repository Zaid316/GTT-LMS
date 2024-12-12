package com.hexaware.gtt.lms.servicesImpl;
 
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
 
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.gtt.lms.dto.Logindto;
import com.hexaware.gtt.lms.dto.PartnerDto;
import com.hexaware.gtt.lms.entities.Partner;
import com.hexaware.gtt.lms.exception.DuplicateDataException;
import com.hexaware.gtt.lms.exception.ResourceDeletionException;
import com.hexaware.gtt.lms.exception.ResourceNotFoundException;
import com.hexaware.gtt.lms.repositories.PartnerRepository;
import com.hexaware.gtt.lms.services.PartnerService;
 
@Service
public class PartnerServiceImpl implements PartnerService {
	@Autowired
	private PartnerRepository partnerRepository;
	@Autowired
	private ModelMapper modelmapper;

 
	public Partner createPartner(PartnerDto ptrdto)throws DuplicateDataException
	{
		if(partnerRepository.findByEmail(ptrdto.getEmail())!=null) {
			System.out.println("35");
			throw new DuplicateDataException("partner","email",ptrdto.getEmail());
		}
		else if(partnerRepository.findByContact(ptrdto.getContact())!=null) {
			throw new DuplicateDataException("partner","contact",ptrdto.getContact().toString());
		}
		else {
			System.out.println("else condition");
			Partner partner=modelmapper.map(ptrdto, Partner.class);
			Partner part = partnerRepository.save(partner);
			return part;
		}	
	}

 
	@Override
	public Partner getPartnerById(UUID id) throws ResourceNotFoundException {
		 if(id==null)
		 {
			 throw new ResourceNotFoundException("Partner", "id", id);
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
	public List<Partner> getAllPartners() throws ResourceNotFoundException {
		List<Partner> ptlist=partnerRepository.findAll();
		if(ptlist==null) {
			throw new ResourceNotFoundException("Partners list is empty");
		}
		else {
			return ptlist;
		}
	}
 
	@Override
	public Partner updatePartner(String email, PartnerDto partnerDto) throws ResourceNotFoundException {
		Partner part=this.partnerRepository.findByEmail(email);
		if(part==null)
		{
			throw new ResourceNotFoundException("Partners", "email", email);
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
	public boolean deletePartner(UUID id) throws ResourceDeletionException {
		Partner part=partnerRepository.findById(id).get();
		if(part.equals(null))
		{
			throw new ResourceDeletionException("Partner "+id+" not found to delete");
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
	
	
	public Logindto loginPartner(String email,String pwd)
	{
		Partner part=partnerRepository.findByEmail(email);
		
		System.out.println("The object is : " +part);
		String emil=part.getEmail();
		
		
		String paswd=part.getPassword();
		if(email.equals(emil)&&pwd.equals(paswd))
		{
			Logindto logdto = this.modelmapper.map(part, Logindto.class);
			logdto.setPartnerId(part.getPartnerId());
			logdto.setEmail(emil);
			return logdto;
		}
		else
		{
			return null;
			}
				
	}


	@Override
	public List<Partner> getApprovedPartners() {
		List<Partner> partList = partnerRepository.findAll();
		List<Partner> approvedPartners = new ArrayList<>();
		for(Partner p: partList) {
			if(p.isStatus()== true && p.isNewPartner()==false) {
				approvedPartners.add(p);
			}
		}
		return approvedPartners;
	}


	@Override
	public List<Partner> getRejectedPartners() {
		List<Partner> partList = partnerRepository.findAll();
		List<Partner> rejectedPartners = new ArrayList<>();
		for(Partner p: partList) {
			if(p.isStatus()== false && p.isNewPartner()==false) {
				rejectedPartners.add(p);
			}
		}
		return rejectedPartners;
	}


	@Override
	public List<Partner> getNewPartners() {
		List<Partner> partList = partnerRepository.findAll();
		List<Partner> newPartners = new ArrayList<>();
		for(Partner p: partList) {
			if(p.isStatus()== false && p.isNewPartner()==true) {
				newPartners.add(p);
			}
		}
		return newPartners;
	}
	
 
 
}