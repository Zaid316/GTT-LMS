package com.hexaware.gtt.lms.servicesImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.hexaware.gtt.lms.dto.CouponGenerationDto;
import com.hexaware.gtt.lms.entities.Coupons;
import com.hexaware.gtt.lms.entities.UserCoupons;
import com.hexaware.gtt.lms.entities.Users;
import com.hexaware.gtt.lms.enums.UserCouponStatus;
import com.hexaware.gtt.lms.repositories.CouponRepository;
import com.hexaware.gtt.lms.repositories.UserCouponRepository;
import com.hexaware.gtt.lms.repositories.UserRepository;
import com.hexaware.gtt.lms.services.UserCouponService;
 
public class UserCouponServiceImpl implements UserCouponService {
 
    @Autowired
    private UserCouponRepository userCouponRepository;
    //private TierRepository tierRepository;
    private UserRepository userRepository;
    private CouponRepository couponRepository;
    
    @Override
    public UserCoupons generateCoupon(CouponGenerationDto couponGenerationDto) {
        String couponCode;
        UUID couponId=couponGenerationDto.getCouponId();
        UUID u_id=couponGenerationDto.getuId();
       Coupons coupon = couponRepository.findById(couponId).orElse(null);
       Users user = userRepository.findById(u_id).orElse(null);

        do {
        	couponCode = generateRandomCouponCode(6);
        } while (userCouponRepository.existsCouponByCouponCode(couponCode));
         UserCoupons newCoupon = new UserCoupons(couponCode, coupon,user , java.time.LocalDateTime.now(), UserCouponStatus.ACTIVE, java.time.LocalDateTime.now(), java.time.LocalDateTime.now());
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
 
//    public String awardCoupon() {
//        if (Math.random() < couponProbability) {
//            return generateUniqueCoupon();
//        } else {
//            return null;
//        }
//    }

    @Override
    public String redeemCoupon(String couponCode, UUID user_id) {
        List<UserCoupons> userCoupons = userCouponRepository.findCouponByUsers_UId(user_id);
        for (UserCoupons coupon : userCoupons ) {
            if (coupon.getCouponCode().equals(couponCode) && coupon.getStatus() == UserCouponStatus.ACTIVE){
                    coupon.setStatus(UserCouponStatus.USED);
                    coupon.setCouponUsedDate(LocalDateTime.now());
                    userCouponRepository.save(coupon);
                }
                
            
            }
        return "coupon does not exist or might be expired";
    }
}
 
