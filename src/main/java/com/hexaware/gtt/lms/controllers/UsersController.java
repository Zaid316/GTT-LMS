package com.hexaware.gtt.lms.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hexaware.gtt.lms.dto.QuitQRegistrationDto;
import com.hexaware.gtt.lms.dto.QuitQResponseDto;
import com.hexaware.gtt.lms.dto.TiersDto;
import com.hexaware.gtt.lms.dto.UserDto;
import com.hexaware.gtt.lms.dto.UserPartnerDto;
import com.hexaware.gtt.lms.dto.UserResponseDto;
import com.hexaware.gtt.lms.entities.Tiers;
import com.hexaware.gtt.lms.entities.Users;
import com.hexaware.gtt.lms.exception.ResourceNotFoundException;
import com.hexaware.gtt.lms.repositories.PartnerRepository;
import com.hexaware.gtt.lms.repositories.TiersRepository;
import com.hexaware.gtt.lms.repositories.UserRepository;
import com.hexaware.gtt.lms.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/lms/users")
public class UsersController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserService userService;

	@Autowired
    private RestTemplate restTemplate;

	@Autowired
	private PartnerRepository partnerRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TiersRepository tierRepository;
	
	//http://localhost:8080/api/v1/lms/users/createUser
	@PostMapping("/createUser")
	public ResponseEntity<?> createUser(@RequestBody QuitQRegistrationDto quitQRegistrationDto){ 
	   try { 
		   Users user=userService.createUsers(quitQRegistrationDto);
		   UserResponseDto userResponseDto=modelMapper.map(user, UserResponseDto.class);
		   return ResponseEntity.ok(userResponseDto);
		 }catch(ResourceNotFoundException e){
			 return  ResponseEntity.ok(e.getMessage()); 		
		 }catch(Exception q){
			 return ResponseEntity.ok(q.getMessage());
		 }	    
	}

	//http://localhost:8080/api/v1/lms/users/getUsers
	@GetMapping("/getUsers")
	public ResponseEntity<List<UserResponseDto>> getUsers(){
		List<Users> userList=userService.getUsers();
		List<UserResponseDto> userResponseDtoList=new ArrayList<>();
		for(Users user:userList) {
			UserResponseDto userResponseDto=modelMapper.map(user, UserResponseDto.class);
			userResponseDtoList.add(userResponseDto);
		}
		return ResponseEntity.ok(userResponseDtoList);
	}

	//http://localhost:8080/api/v1/lms/users/getUsers
	@GetMapping("/getUserById")
	public ResponseEntity<UserResponseDto> getUserById(@RequestParam("uId") UUID uid) throws ResourceNotFoundException{
		Users user = this.userService.getUserById(uid);
		Long userId = user.getUserId();
		QuitQResponseDto quitQResponseDto = restTemplate.getForObject("http://localhost:8081/api/v1/quitq/customer/getcustomer/{id}", QuitQResponseDto.class, userId);
		UserResponseDto userResponseDto= this.modelMapper.map(user,UserResponseDto.class);
		userResponseDto.setQuitQResponseDto(quitQResponseDto);
		return ResponseEntity.ok(userResponseDto);
	}

	//http://localhost:8080/api/v1/lms/users/getUserById?uId=fad0d750-f86f-42be-93d6-db56263eecac
	@GetMapping("/getUserIdById")
	public ResponseEntity<UserResponseDto>  getUserIdById(@RequestParam("uId") UUID uid) throws ResourceNotFoundException{
		Users user = this.userService.getUserById(uid);
		Long userId = user.getUserId();
		QuitQResponseDto quitQResponseDto = restTemplate.getForObject("http://localhost:8081/api/v1/quitq/customer/getcustomer/{id}", QuitQResponseDto.class, userId);
		UserResponseDto userResponseDto= this.modelMapper.map(user,UserResponseDto.class);
		userResponseDto.setQuitQResponseDto(quitQResponseDto);
		return ResponseEntity.ok(userResponseDto);
	}

	@GetMapping("/getUserTier")
	public ResponseEntity<TiersDto> getUserTier(@RequestParam Long userId,@RequestParam UUID partnerId) throws ResourceNotFoundException{
		UserPartnerDto userPartnerDto = new UserPartnerDto(userId, partnerId);
		Tiers tier = userService.getUserTier(userPartnerDto);
		TiersDto tiersDto =  this.modelMapper.map(tier,TiersDto.class);
		return ResponseEntity.ok(tiersDto);
	}
	
	//http://localhost:8080/api/v1/lms/users/updateUser?uId=fad0d750-f86f-42be-93d6-db56263eecac
	@PutMapping("/updateUser")
   public ResponseEntity<UserResponseDto> updateUsers(@RequestBody UserDto usersDto,@RequestParam("uId") UUID u_id) throws ResourceNotFoundException {
		
		Users user=this.userService.updateUser(usersDto,u_id);
		UserResponseDto userResponseDto= this.modelMapper.map(user,UserResponseDto.class);
		return ResponseEntity.ok(userResponseDto);
	}
		
	//http://localhost:8080/api/v1/lms/users/deleteUser?uId=0bc80825-5a2c-4707-bf79-c1f71e8223c8
	@DeleteMapping("/deleteUser")
	public ResponseEntity<String> deleteUser(@RequestParam("uId") UUID u_id) throws ResourceNotFoundException {
		String s = this.userService.deleteUser(u_id);
		return ResponseEntity.ok(s);
	}
	

}
