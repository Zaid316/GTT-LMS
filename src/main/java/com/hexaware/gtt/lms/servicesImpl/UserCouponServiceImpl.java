package com.hexaware.gtt.lms.servicesImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.gtt.lms.dto.CouponGenerationDto;
import com.hexaware.gtt.lms.dto.UserCouponDto;
import com.hexaware.gtt.lms.dto.UserPartnerDto;
import com.hexaware.gtt.lms.dto.UserValidationDto;
import com.hexaware.gtt.lms.entities.Coupons;
import com.hexaware.gtt.lms.entities.UserCoupons;
import com.hexaware.gtt.lms.entities.Users;
import com.hexaware.gtt.lms.enums.UserCouponStatus;
import com.hexaware.gtt.lms.repositories.CouponRepository;
import com.hexaware.gtt.lms.repositories.PartnerRepository;
import com.hexaware.gtt.lms.repositories.TiersRepository;
import com.hexaware.gtt.lms.repositories.UserCouponRepository;
import com.hexaware.gtt.lms.repositories.UserRepository;
import com.hexaware.gtt.lms.services.UserCouponService;

@Service
public class UserCouponServiceImpl implements UserCouponService {

	@Autowired
	private UserCouponRepository userCouponRepository;
	@Autowired
	private TiersRepository tierRepository;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	public UserCouponServiceImpl(UserCouponRepository userCouponRepository, TiersRepository tierRepository,
			UserRepository userRepository, CouponRepository couponRepository, ModelMapper modelMapper) {
		super();
		this.userCouponRepository = userCouponRepository;
		this.tierRepository = tierRepository;
		this.userRepository = userRepository;
		this.couponRepository = couponRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public UUID finduid(UserPartnerDto userPartnerDto) {
		return userRepository.findUIdByPartnerIdAndUserId(userPartnerDto.getPartnerId(), userPartnerDto.getUserId());
	}

	@Override
	public UUID findTierbyUId(UUID u_Id) {
		return this.userRepository.getTierByUId(u_Id);
	}

	float couponProbability = 0.0f;

	@Override
	public float findProbablity(UUID tier_id) {
		couponProbability += this.tierRepository.getCouponProbablityByTierId(tier_id);
		return this.tierRepository.getCouponProbablityByTierId(tier_id);
	}

	@Override
	public UUID getCouponId(UUID tierId) {
		List<UUID> fetchedCouponIds = fetchCouponIdsForTier(tierId);
		System.out.println("tierid" + tierId);
		System.out.println(fetchedCouponIds);
		if (!fetchedCouponIds.isEmpty()) {
			Random random = new Random();
			int randomIndex = random.nextInt(fetchedCouponIds.size());
			System.out.println("fetchedCouponIds");
			return fetchedCouponIds.get(randomIndex);
		}
		return null;

	}

	@Override
	public List<UUID> fetchCouponIdsForTier(UUID tierId) {
		List<UUID> CouponIds = this.couponRepository.findCouponsByTierId(tierId);
		System.out.println("coupons : " + CouponIds.size());
		return CouponIds;
	}

	@Override
	public boolean awardCoupon(float couponProbability) {
		if (Math.random() < couponProbability) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public UserCoupons generateCoupon(CouponGenerationDto couponGenerationDto) {
		String couponCode;
		UUID couponId = couponGenerationDto.getCouponId();
		UUID u_id = couponGenerationDto.getuId();
		Coupons coupon = couponRepository.findById(couponId).orElse(null);
		int validity = coupon.getValidity();
		Users user = userRepository.findById(u_id).orElse(null);

		do {
			couponCode = generateRandomCouponCode(6);
		} while (userCouponRepository.existsById(couponCode));
		UserCoupons newCoupon = new UserCoupons(couponCode, coupon, user, java.time.LocalDateTime.now(),
				UserCouponStatus.ACTIVE, java.time.LocalDateTime.now().plusDays(validity));
		return userCouponRepository.save(newCoupon);

	}

	@Override
	public String generateRandomCouponCode(int length) {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuilder couponCode = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			couponCode.append(characters.charAt(random.nextInt(characters.length())));
		}
		return couponCode.toString();
	}

	@Override
	public boolean validateCoupon(UserValidationDto userValidationDto) {
		List<UserCoupons> userCoupons = this.userCouponRepository.findUserCouponsByUsers_UId(userValidationDto.getuId());
		System.out.println("checked coupons" + userCoupons);
		for (UserCoupons coupon : userCoupons) {
			if (coupon.getCouponCode().equals(userValidationDto.getCouponCode())){
				if(coupon.getStatus() == UserCouponStatus.ACTIVE && !isCouponExpired(coupon)){
					return true;
				}
				else if(isCouponExpired(coupon)){
					coupon.setStatus(UserCouponStatus.EXPIRED);
					this.userCouponRepository.save(coupon);
					return false;
				}
				else{
					return false;
				}
			}
	
		}
				return false;

	}
	

	@Override
	public boolean redeemCoupon(UserValidationDto userValidationDto){
//		UserCoupons userCoupons = this.modelMapper.map(userValidationDto,UserCoupons.class);
		if(validateCoupon(userValidationDto)){
			UserCoupons userCoupon = userCouponRepository.findByCouponCode(userValidationDto.getCouponCode());
			userCoupon.setStatus(UserCouponStatus.USED);
			userCoupon.setCouponUsedDate(LocalDateTime.now());
            this.userCouponRepository.save(userCoupon);
            return true;
        }
 
        return false;
 
 
    }

    private boolean isCouponExpired(UserCoupons coupon){
		System.out.println(coupon.getExpiry());
		System.out.println(LocalDateTime.now());
        return coupon.getExpiry().isBefore(LocalDateTime.now());
    }
    
    @Override
	public List<UserCoupons> listOfActiveCoupons(UUID uId){
		List<UserCoupons> userCoupons=this.userCouponRepository.getAllUsers(uId);
		List <UserCoupons> ActiveCoupons=new ArrayList<>();
		for(UserCoupons u: userCoupons) {
		ActiveCoupons.add(updateStatus(u));
		}
		return ActiveCoupons;
	}
	@Override
	public List<UserCoupons> listOfAllCoupons(UUID uId){

		List<UserCoupons> listOfAll=this.userCouponRepository.getAllUsers(uId);
		List <UserCoupons> updatedCoupons=new ArrayList<>();
		for(UserCoupons u:listOfAll) {
			updatedCoupons.add(updateStatus(u));
		}
		return updatedCoupons;
		

	}
	
	public UserCoupons updateStatus(UserCoupons userCoupons) {
	
		if(userCoupons.getStatus()==UserCouponStatus.ACTIVE && userCoupons.getExpiry().compareTo(java.time.LocalDateTime.now())<0) {
			userCoupons.setStatus(UserCouponStatus.EXPIRED);
		}
		return userCoupons;
	}

	@Override
	public UUID finduidbyuserAndPartner(Long userId,UUID PartnerId) {
		return userRepository.findUIdByPartnerIdAndUserId(PartnerId, userId);
		
	}
}
