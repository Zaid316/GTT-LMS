package com.hexaware.gtt.lms.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.gtt.lms.dto.PartnerDto;
import com.hexaware.gtt.lms.dto.ProgramDto;
import com.hexaware.gtt.lms.dto.ProgramRequestDto;
import com.hexaware.gtt.lms.entities.Partner;
import com.hexaware.gtt.lms.entities.Program;
import com.hexaware.gtt.lms.exception.ResourceDeletionException;
import com.hexaware.gtt.lms.exception.ResourceNotFoundException;
import com.hexaware.gtt.lms.services.ProgramService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/lms/programs")
public class ProgramController {
	private ProgramService programService;
	private ModelMapper modelMapper;
	@Autowired
	public ProgramController(ProgramService programService,  ModelMapper modelMapper) {
		this.programService = programService;
		this.modelMapper = modelMapper;
	}
		@PostMapping("/createProgram")
		public ResponseEntity<?> createProgram(@RequestBody ProgramRequestDto programRequest){
			Program program = programService.createProgram(programRequest);
			
			return new ResponseEntity<>("Program created Successfully with ID: "+program.getProgramId(), HttpStatus.CREATED);
		}
		
		@GetMapping("/getAllPrograms")
		public ResponseEntity<List<ProgramDto>> getProgarms(){
			List<Program> programsList=this.programService.getAllPrograms();
			List<ProgramDto> programDtoList=new ArrayList<>();
			for(Program program : programsList) {
				ProgramDto programDto=this.modelMapper.map(program, ProgramDto.class);
				programDtoList.add(programDto);
				
			}
			return ResponseEntity.ok(programDtoList);
			
			
		}
		
		@GetMapping("/getAllPartnerPrograms")
		public ResponseEntity<List<ProgramDto>> getPartnerProgarms(@RequestParam("partner_id") UUID partnerId){
			List<Program> programsList=this.programService.getAllPartnerPrograms(partnerId);
			List<ProgramDto> programDtoList=new ArrayList<>();
			for(Program program : programsList) {
				ProgramDto programDto=this.modelMapper.map(program, ProgramDto.class);
				programDtoList.add(programDto);
				
			}
			return ResponseEntity.ok(programDtoList);
			
			
		}
		
		@PutMapping("/updateProgram")
		public ResponseEntity<?> updatePartner(@Valid @RequestBody ProgramDto programDto){
			try {
				Program program = programService.updateProgram(programDto);
				ProgramDto prgDto = modelMapper.map(program, ProgramDto.class);
				return ResponseEntity.ok(prgDto);
			}catch(ResourceNotFoundException e){
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}
		}
		
		
		@DeleteMapping("/deleteProgramById")
		public ResponseEntity<?> deleteProgram(@RequestParam("id") UUID id){
			try {
				boolean status = programService.deleteProgram(id);
				if(status) {
					return ResponseEntity.ok("Successfully deleted program");
				}
				else {
					return ResponseEntity.ok("Unsuccessfull deletion attempt");
				}
			}catch(ResourceDeletionException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}
		}

		
}
