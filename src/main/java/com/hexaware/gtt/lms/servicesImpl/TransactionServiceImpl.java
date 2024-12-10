package com.hexaware.gtt.lms.servicesImpl;
 
import java.time.LocalDateTime;

import java.time.temporal.ChronoUnit;

import java.util.Comparator;

import java.util.List;

import java.util.UUID;
 
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
 
import com.hexaware.gtt.lms.dto.PointsAmountRequestDto;

import com.hexaware.gtt.lms.dto.PointsAmountResponseDto;

import com.hexaware.gtt.lms.dto.TransactionDto;

import com.hexaware.gtt.lms.dto.TransactionRequestDto;

import com.hexaware.gtt.lms.dto.UserCouponRequestDto;

import com.hexaware.gtt.lms.dto.UserCouponResponseDto;

import com.hexaware.gtt.lms.dto.UserValidationDto;

import com.hexaware.gtt.lms.entities.Coupons;

import com.hexaware.gtt.lms.entities.Tiers;

import com.hexaware.gtt.lms.entities.Transactions;

import com.hexaware.gtt.lms.entities.UserCoupons;

import com.hexaware.gtt.lms.entities.Users;
import com.hexaware.gtt.lms.repositories.CouponRepository;

import com.hexaware.gtt.lms.repositories.TiersRepository;

import com.hexaware.gtt.lms.repositories.TransactionRepository;

import com.hexaware.gtt.lms.repositories.UserCouponRepository;

import com.hexaware.gtt.lms.repositories.UserRepository;

import com.hexaware.gtt.lms.services.TiersService;

import com.hexaware.gtt.lms.services.TransactionService;

import com.hexaware.gtt.lms.services.UserCouponService;

import com.hexaware.gtt.lms.services.UserService;

@Service

public class TransactionServiceImpl implements TransactionService{
 
	@Autowired

	private ModelMapper modelMapper;

	@Autowired

	private UserRepository userRepository;

	@Autowired

	private TiersRepository tiersRepository;

	@Autowired

	private UserCouponRepository userCouponRepository;

	@Autowired

	private UserCouponService userCouponService;

	@Autowired

	private TransactionRepository transactionRepository;

	@Autowired

	private CouponRepository couponRepository;

	@Autowired

	private TiersService tiersService;

	@Autowired

	private UserService userService;
 
	


	@Override

	public PointsAmountResponseDto finalAmount(PointsAmountRequestDto pointsAmountRequestDto) {

		UUID uId=userRepository.findUIdByPartnerIdAndUserId(pointsAmountRequestDto.getPartnerId(),pointsAmountRequestDto.getUserId());

		Users user=userRepository.findByUId(uId);

		Tiers tiers=tiersRepository.findById(user.getTiers().getTierId()).get();

		Double amountAbleToSpentUsingCoins=(tiers.getRedemptionLimitOfPurchase())*pointsAmountRequestDto.getAmount();

		Double pointsToUse=amountAbleToSpentUsingCoins/tiers.getConversion();

		Double amountToBePaid;

		PointsAmountResponseDto pointsAmountResponseDto=new PointsAmountResponseDto();

		if(pointsToUse<=user.getTotalPoints()) {

			amountToBePaid=pointsAmountRequestDto.getAmount()-amountAbleToSpentUsingCoins;

			//user.setTotalPoints(user.getTotalPoints()-pointsToUse);

			pointsAmountResponseDto.setSpentPoints(pointsToUse);

			pointsAmountResponseDto.setAmountToBePaid(amountToBePaid);

		}

		else {

			Double amountAvailabletoSpendUsingCoins=user.getTotalPoints()*tiers.getConversion();

			amountToBePaid=pointsAmountRequestDto.getAmount()-amountAvailabletoSpendUsingCoins;

			//user.setTotalPoints(0.0);

			pointsAmountResponseDto.setSpentPoints(user.getTotalPoints());

			pointsAmountResponseDto.setAmountToBePaid(amountToBePaid);

		}

		//user.setTotalPoints(user.getTotalPoints()+(tiers.getAccrualMultiplier()*amountToBePaid));

		pointsAmountResponseDto.setReceivedPoints(tiers.getAccrualMultiplier()*amountToBePaid);

		return pointsAmountResponseDto;

	}
 
	@Override

	public UserCouponResponseDto applyCoupon(UserCouponRequestDto userCouponRequestDto){

		UserCoupons userCoupon = userCouponRepository.findByCouponCode(userCouponRequestDto.getCouponCode());

		//System.out.println("usercouponretrived: " + userCoupon);

		Coupons coupon = userCoupon.getCoupons();

		//System.out.println("couponidretrived: " +  coupon );

		Double discountPercentage = coupon.getPercentage();

		Double maxLimit = coupon.getMaxLimit();

		Double discountedAmt = (discountPercentage)*(userCouponRequestDto.getAmount());

		UserCouponResponseDto userCouponResponseDto = new UserCouponResponseDto();

		UserValidationDto userValidationDto = new UserValidationDto();

		userValidationDto.setCouponCode(userCouponRequestDto.getCouponCode());

		userValidationDto.setuId(userCouponRequestDto.getuId());

		if(userCouponService.validateCoupon(userValidationDto)){

			if(discountedAmt<=maxLimit){

			userCouponResponseDto.setAmountDiscounted(discountedAmt);

			userCouponResponseDto.setFinalAmount(userCouponRequestDto.getAmount() - discountedAmt);

		}

		else{

			userCouponResponseDto.setAmountDiscounted(maxLimit);

			userCouponResponseDto.setFinalAmount(userCouponRequestDto.getAmount() - maxLimit);
 
		}}

		return userCouponResponseDto;
 
 
	}
	
	public Users userTierUpdationTransaction(Users settedUser, TransactionRequestDto transactionRequestDto, UUID uId) throws Exception {
		List<Tiers> tiersList = tiersService.getallTiersbyPartnerId(settedUser.getPartner().getPartnerId());

		tiersList.sort(Comparator.comparingDouble(Tiers::getTriggerAmount));

		if(settedUser.getTiers().getTriggerAmount()==0) {

			Tiers myTier = settedUser.getTiers();

			for(Tiers tier: tiersList) {

				if(settedUser.getTierTransactionAmount()>=tier.getTriggerAmount()) {

					myTier = tier;
				}

				else {					

					break;

				}				

			}	

			if(myTier.getTierId()!= settedUser.getTiers().getTierId()) {

				System.out.println("myTier2: \n"+myTier);

				 Users savedUser = userService.updateUsersTier(uId, myTier);

				 savedUser.setTierTransactionAmount(0);	

				 savedUser.setTierSetDate(LocalDateTime.now());

				 savedUser.setExpiry(LocalDateTime.now().plus(myTier.getTriggerDuration(), ChronoUnit.MONTHS));

				userRepository.save(savedUser);

				return savedUser;

			}

		}

		else {

			Tiers myTier = settedUser.getTiers();

			for(Tiers tier: tiersList) {

				if(settedUser.getTierTransactionAmount()>=tier.getTriggerAmount()) {
					myTier = tier;
				}

				else {

					break;

					}

				}

			if(myTier.getTierId()!=settedUser.getTiers().getTierId()) {

				if(settedUser.getExpiry()!= LocalDateTime.now() && myTier.getTriggerAmount()!=0) {

				Users savedUser = userService.updateUsersTier(uId, myTier);
				savedUser.setTierTransactionAmount(0);	
				savedUser.setTierSetDate(LocalDateTime.now());
				savedUser.setExpiry(LocalDateTime.now().plus(myTier.getTriggerDuration(), ChronoUnit.MONTHS));
				Users finalUser = userRepository.save(savedUser);
				return finalUser;

				}else if(myTier.getTriggerAmount()==0 && settedUser.getExpiry()== LocalDateTime.now()) {
					Users savedUser = userService.updateUsersTier(uId, myTier);
					savedUser.setTierTransactionAmount(0);	
					savedUser.setTierSetDate(LocalDateTime.now());
					savedUser.setExpiry(null);
					Users finalUser = userRepository.save(savedUser);
					return finalUser;					

				}
			}

			else {
				settedUser.setTierTransactionAmount(settedUser.getTierTransactionAmount()+transactionRequestDto.getAmount());
				Users finalUser = userRepository.save(settedUser);
				return finalUser;
			}

		}
		return settedUser;

	}
 
	@Override

	public TransactionDto createTransaction(TransactionRequestDto transactionRequestDto) throws Exception {

		Transactions transaction =  modelMapper.map(transactionRequestDto, Transactions.class); 

//		 Transactions(UUID transId, Users users, long paymentId, Coupons coupons, TransType transactionType,

//					double pointsGained, double pointsSpent, double amount)

		UUID uId=userRepository.findUIdByPartnerIdAndUserId(transactionRequestDto.getPartnerId(),transactionRequestDto.getUserId());
		Users user=userRepository.findByUId(uId);
		user.setTotalPoints(user.getTotalPoints()+transactionRequestDto.getPointsGained()-transactionRequestDto.getPointsSpent());
		//Tier Updation for user
		user.setTotalTransactionAmount(user.getTotalTransactionAmount()+transactionRequestDto.getAmount());
		user.setTierTransactionAmount(user.getTierTransactionAmount()+transactionRequestDto.getAmount());
		Users settedUser = userRepository.save(user);
		Users finalUser = userTierUpdationTransaction(settedUser,transactionRequestDto,uId);
		transaction.setUsers(finalUser);
		transaction.setCoupons(couponRepository.getById(transactionRequestDto.getCouponId()));
		transactionRepository.save(transaction);
		TransactionDto transactionDto =  modelMapper.map(transaction, TransactionDto.class);
		return transactionDto;

	}

 
}

 