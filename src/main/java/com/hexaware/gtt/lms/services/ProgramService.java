package com.hexaware.gtt.lms.services;

import java.util.List;
import java.util.UUID;

import com.hexaware.gtt.lms.dto.ProgramDto;
import com.hexaware.gtt.lms.dto.ProgramRequestDto;
import com.hexaware.gtt.lms.entities.Partner;
import com.hexaware.gtt.lms.entities.Program;
import com.hexaware.gtt.lms.exception.ResourceDeletionException;
import com.hexaware.gtt.lms.exception.ResourceNotFoundException;

public interface ProgramService {

	public Program createProgram(ProgramRequestDto programRequest);
	public List<Program> getAllPrograms();
	public List<Program> getAllPartnerPrograms(UUID partnerId) ;
	public Program updateProgram(ProgramDto programDto) throws ResourceNotFoundException;
	public boolean deleteProgram(UUID id) throws ResourceDeletionException;
	public Program getProgramById(UUID id);
	public UUID getCurrentProgram(UUID partnerId);
}
