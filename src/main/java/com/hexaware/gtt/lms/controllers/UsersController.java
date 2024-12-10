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
import com.hexaware.gtt.lms.dto.UserDto;
import com.hexaware.gtt.lms.dto.UserResponseDto;
import com.hexaware.gtt.lms.entities.Partner;
import com.hexaware.gtt.lms.entities.Tiers;
import com.hexaware.gtt.lms.entities.Users;
import com.hexaware.gtt.lms.exception.ResourceNotFoundException;
import com.hexaware.gtt.lms.repositories.PartnerRepository;
import com.hexaware.gtt.lms.repositories.TiersRepository;
import com.hexaware.gtt.lms.repositories.UserRepository;
import com.hexaware.gtt.lms.services.UserService;

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
	
	@PostMapping("/createUser")
	public ResponseEntity<UserResponseDto> createUser(@RequestBody QuitQRegistrationDto quitQRegistrationDto) throws ResourceNotFoundException{
	    Users user=userService.createUsers(quitQRegistrationDto);
	    UserResponseDto userResponseDto=modelMapper.map(user, UserResponseDto.class);
	    return ResponseEntity.ok(userResponseDto);
	    
	}

	
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

	@GetMapping("/getUserById")
	public ResponseEntity<UserResponseDto> getUserById(@RequestParam("uId") UUID uid) throws ResourceNotFoundException{
		Users user = this.userService.getUserById(uid);
		UserResponseDto userResponseDto= this.modelMapper.map(user,UserResponseDto.class);
		return ResponseEntity.ok(userResponseDto);
	}

	@GetMapping("/getUserIdById")
	public ResponseEntity<UserResponseDto>  getUserIdById(@RequestParam("uId") UUID uid) throws ResourceNotFoundException{
		Users user = this.userService.getUserById(uid);
		Long userId = user.getUserId();
		QuitQResponseDto quitQResponseDto = restTemplate.getForObject("http://localhost:8081/api/v1/quitq/customer/getcustomer/{id}", QuitQResponseDto.class, userId);
		UserResponseDto userResponseDto= this.modelMapper.map(user,UserResponseDto.class);
		userResponseDto.setQuitQResponseDto(quitQResponseDto);
		return ResponseEntity.ok(userResponseDto);
	}

	
	@PutMapping("/updateUser")
   public ResponseEntity<UserResponseDto> updateUsers(@RequestBody UserDto usersDto,@RequestParam("uId") UUID u_id) throws ResourceNotFoundException {
		
		Users user=this.userService.updateUser(usersDto,u_id);
		UserResponseDto userResponseDto= this.modelMapper.map(user,UserResponseDto.class);
		return ResponseEntity.ok(userResponseDto);
	}
		
	@DeleteMapping("/deleteUsers")
	public ResponseEntity<String> deleteUser(@RequestParam("uId") UUID u_id) throws ResourceNotFoundException {
		String s = this.userService.deleteUser(u_id);
		return ResponseEntity.ok(s);
	}
	

}
