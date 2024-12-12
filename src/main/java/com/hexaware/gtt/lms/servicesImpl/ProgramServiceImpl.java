package com.hexaware.gtt.lms.servicesImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.gtt.lms.dto.ProgramDto;
import com.hexaware.gtt.lms.dto.ProgramRequestDto;
import com.hexaware.gtt.lms.entities.Partner;
import com.hexaware.gtt.lms.entities.Program;
import com.hexaware.gtt.lms.exception.ResourceDeletionException;
import com.hexaware.gtt.lms.exception.ResourceNotFoundException;
import com.hexaware.gtt.lms.repositories.PartnerRepository;
import com.hexaware.gtt.lms.repositories.ProgramRepository;
import com.hexaware.gtt.lms.services.ProgramService;

@Service
public class ProgramServiceImpl implements ProgramService {

	@Autowired
	private ProgramRepository programRepository;
	@Autowired
	private PartnerRepository partnerRepository;

	@Override
	public Program createProgram(ProgramRequestDto programRequest) {
		Program program = new Program();
		program.setProgramName(programRequest.getProgramName());
		Partner partner = partnerRepository.findById(programRequest.getPartnerId()).orElse(null);
		program.setPartner(partner);
		program.setStatus(programRequest.isStatus());
        program.setStartDate(programRequest.getStartDate());
        program.setEndDate(programRequest.getEndDate());
		return programRepository.save(program);
	}

	@Override
	public List<Program> getAllPrograms() {
		return this.programRepository.findAll();
	}

	@Override
	    public List<Program> getAllPartnerPrograms(UUID partnerId) {
		 Partner partner = partnerRepository.findById(partnerId).orElseGet(null);
	        return this.programRepository.findAllProgramByPartner(partner);
	    }
	
	@Override
	public Program updateProgram(ProgramDto programDto) throws ResourceNotFoundException{
		Program prg=this.programRepository.findById(programDto.getProgramId()).orElse(null);
		if(prg==null)
		{
			throw new ResourceNotFoundException("Program", "email", programDto.getProgramId());
		}
		else {
			prg.setProgramName(programDto.getProgramName());
			prg.setStartDate(programDto.getStartDate());
			prg.setEndDate(programDto.getEndDate());
			prg.setStatus(programDto.isStatus());
			Program savedProgram = programRepository.save(prg);
			return savedProgram;
		}
	}
 
	@Override
	public boolean deleteProgram(UUID id) throws ResourceDeletionException{
		Program prg=programRepository.findById(id).get();
		if(prg.equals(null))
		{
			throw new ResourceDeletionException("Program "+id+" not found to delete");
		}
		programRepository.deleteById(id);		
		if(programRepository.existsById(id))
			{
			return false;
			}
		else 
		{
			return true;
		}
	}

	@Override
	public Program getProgramById(UUID id) {
		Program prg=this.programRepository.findById(id).orElse(null);
		return prg;
	}

	@Override
	public UUID getCurrentProgram(UUID partnerId) {
		List<UUID> programs = programRepository.findCurrentProgram(partnerId);
		return programs.get(0);
	}
	
	
}
