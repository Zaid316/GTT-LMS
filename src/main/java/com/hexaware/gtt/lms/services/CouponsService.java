package com.hexaware.gtt.lms.services;
 
import java.util.List;
import java.util.UUID;
 
import com.hexaware.gtt.lms.dto.CouponsDto;
import com.hexaware.gtt.lms.entities.Coupons;
import com.hexaware.gtt.lms.exception.ResourceNotFoundException;
 
 
public interface CouponsService {
	public Coupons createCoupons(CouponsDto couponsDto) throws ResourceNotFoundException;
	public List<Coupons> getCoupons();
	public Coupons updateCoupons(CouponsDto couponsDto,UUID coupon_id) throws ResourceNotFoundException;
	public String deleteCoupons(UUID coupon_id) throws ResourceNotFoundException;
	public Coupons getCouponsById(UUID coupon_id) throws ResourceNotFoundException;
	public List<Coupons> getCouponsByProgramId(UUID programId) throws ResourceNotFoundException;
}
 
 