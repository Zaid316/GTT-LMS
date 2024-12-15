package com.hexaware.gtt.lms.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.gtt.lms.entities.Partner;
import com.hexaware.gtt.lms.entities.Program;
@Repository
public interface ProgramRepository  extends JpaRepository<Program, UUID>{

	List<Program> findAllProgramByPartner(Partner partner);

	 @Query("SELECT p.programId FROM Program p WHERE p.defaultProgram = false AND p.status = true AND p.partner.partnerId = :partnerId")
	    List<UUID> findCurrentProgram(@Param("partnerId") UUID partnerId);
	 
	 @Query("SELECT p.programId FROM Program p WHERE p.defaultProgram = true AND p.status = true AND p.partner.partnerId = :partnerId")
	    UUID findDefaultProgram(@Param("partnerId") UUID partnerId);
}
