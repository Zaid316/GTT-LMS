package com.hexaware.gtt.lms.servicesImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.gtt.lms.dto.CouponGenerationDto;
import com.hexaware.gtt.lms.dto.UserPartnerDto;
import com.hexaware.gtt.lms.entities.Coupons;
import com.hexaware.gtt.lms.entities.UserCoupons;
import com.hexaware.gtt.lms.entities.Users;
import com.hexaware.gtt.lms.enums.UserCouponStatus;
import com.hexaware.gtt.lms.repositories.CouponRepository;
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
	public UserCouponServiceImpl(UserCouponRepository userCouponRepository, TiersRepository tierRepository,
			UserRepository userRepository, CouponRepository couponRepository) {
		super();
		this.userCouponRepository = userCouponRepository;
		this.tierRepository = tierRepository;
		this.userRepository = userRepository;
		this.couponRepository = couponRepository;
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
	public String redeemCoupon(String couponCode, UUID user_id) {
		List<UserCoupons> userCoupons = userCouponRepository.findCouponByUsers_UId(user_id);
		for (UserCoupons coupon : userCoupons) {
			if (coupon.getCouponCode().equals(couponCode) && coupon.getStatus() == UserCouponStatus.ACTIVE) {
				coupon.setStatus(UserCouponStatus.USED);
				coupon.setCouponUsedDate(LocalDateTime.now());
				userCouponRepository.save(coupon);
			}

		}
		return "coupon does not exist or might be expired";
	}
}
