package com.hexaware.gtt.lms.repositories;
 
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.gtt.lms.entities.Offers;
import com.hexaware.gtt.lms.entities.Program;
 
@Repository
public interface OffersRepository extends JpaRepository<Offers,UUID>{
	public List<Offers> findAllOffersByProgram(Program program);
	@Query("SELECT o FROM Offers o WHERE o.program.programId = :programId AND o.tiers.tierId = :tierId")
    List<Offers> findOffersByProgramAndTier(@Param("programId") UUID programId, @Param("tierId") UUID tierId);
}
 
 