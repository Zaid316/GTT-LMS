package com.hexaware.gtt.lms.servicesImpl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.gtt.lms.dto.QuitQRegistrationDto;
import com.hexaware.gtt.lms.dto.UserDto;
import com.hexaware.gtt.lms.dto.UserPartnerDto;
import com.hexaware.gtt.lms.entities.Partner;
import com.hexaware.gtt.lms.entities.Tiers;
import com.hexaware.gtt.lms.entities.Users;
import com.hexaware.gtt.lms.exception.ResourceNotFoundException;
import com.hexaware.gtt.lms.repositories.PartnerRepository;
import com.hexaware.gtt.lms.repositories.TiersRepository;
import com.hexaware.gtt.lms.repositories.UserRepository;
import com.hexaware.gtt.lms.services.UserService;

import jakarta.validation.Valid;

@Service
public class UserServiceImpl implements UserService {
        @Autowired
        private ModelMapper modelMapper;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private TiersRepository tiersRepository;

        @Autowired
        private PartnerRepository partnerRepository;

        @Autowired
        private TiersRepository tierRepository;

        @Override
        public Users createUsers(QuitQRegistrationDto quitQRegistrationDto) throws ResourceNotFoundException {
                Users user = new Users();
                Long userId = quitQRegistrationDto.getUserId();
                System.out.println("userid: " + userId);
                user.setUserId(userId);
                Partner partner = partnerRepository.findByPartnerId(quitQRegistrationDto.getPartnerId());
                System.out.println("partnerid: " + partner);
                user.setPartner(partner);
                List<Tiers> tiers = tierRepository.findByPartner(partner);
                for (Tiers tier : tiers) {
                        if (tier.getTriggerAmount() == 0) {
                                user.setTiers(tier);
                                if(tier.getTriggerDuration() > 0) {
                                user.setExpiry(java.time.LocalDateTime.now().plusDays(tier.getTriggerDuration()));
                                }
                        }
                }
                user.setTotalPoints(0);
                return userRepository.save(user);
        }

        @Override
        public List<Users> getUsers() {
                return userRepository.findAll();
        }

        @Override
        public Users getUserById(UUID uId) throws ResourceNotFoundException {
                return userRepository.findById(uId)
                                .orElseThrow(() -> new ResourceNotFoundException("User", "id", uId));
        }

        @Override
        public Users updateUser(UserDto userDto, UUID uId) throws ResourceNotFoundException {
                Users users = userRepository.findById(uId)
                                .orElseThrow(() -> new ResourceNotFoundException("User", "id", uId));

                users.setUserId(userDto.getUserId());
                users.setTotalPoints(userDto.getTotalPoints());
                users.setExpiry(userDto.getExpiry());

                Partner partners = partnerRepository.findById(userDto.getPartnerId())
                                .orElseThrow(() -> new ResourceNotFoundException("Partner", "id",
                                                userDto.getPartnerId()));
                users.setPartner(partners);

//                Tiers tiers = tiersRepository.findById(userDto.getTierId())
//                                .orElseThrow(() -> new ResourceNotFoundException("Tier", "id", userDto.getTierId()));
//                users.setTiers(tiers);
                
                //user can not update tiers

                return userRepository.save(users);
        }
        
        @Override
        public Users updateUsersTier(UUID uid,Tiers tier) throws ResourceNotFoundException {
        	System.out.println("entered update tier method");
        	Users user = userRepository.findById(uid).orElseThrow(() -> new ResourceNotFoundException("User", "uid", uid));
        	user.setTiers(tier);
        	return userRepository.save(user);
        }
     

        @Override
        public String deleteUser(UUID uId) throws ResourceNotFoundException {
                Users users = userRepository.findById(uId)
                                .orElseThrow(() -> new ResourceNotFoundException("User", "id", uId));
                userRepository.delete(users);
                return "User " + uId + " deleted successfully";
        }

		@Override
		public Tiers getUserTier(@Valid UserPartnerDto userPartnerDto) {
			UUID uId = userRepository.findUIdByPartnerIdAndUserId(userPartnerDto.getPartnerId(), userPartnerDto.getUserId());
			UUID tierId= userRepository.getTierByUId(uId);
			return this.tiersRepository.findById(tierId).get();
		}

		@Override
		public List<Users> getUsersByPartner(UUID partnerId) {
			Partner partner = partnerRepository.findByPartnerId(partnerId);
			return userRepository.findAllByPartner(partner);
		}
}
