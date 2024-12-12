package com.hexaware.gtt.lms.services;

import java.util.List;
import java.util.UUID;

import com.hexaware.gtt.lms.dto.FreeTiersDto;
import com.hexaware.gtt.lms.dto.TiersDto;
import com.hexaware.gtt.lms.entities.Tiers;

public interface TiersService {
	public Tiers createTier(TiersDto ti);
	public boolean deleteTier(UUID id);
	public List<Tiers> getallTiersbyPartnerId(UUID id) throws Exception;
	public Tiers createFreeTier(FreeTiersDto frdto);
	public Tiers findTierbyTierId(UUID id) throws Exception;
}
