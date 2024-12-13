package com.hexaware.gtt.lms.services;

import java.util.List;
import java.util.UUID;

import com.hexaware.gtt.lms.dto.QuitQRegistrationDto;
import com.hexaware.gtt.lms.dto.UserDto;
import com.hexaware.gtt.lms.dto.UserPartnerDto;
import com.hexaware.gtt.lms.entities.Tiers;
import com.hexaware.gtt.lms.entities.Users;
import com.hexaware.gtt.lms.exception.ResourceNotFoundException;

import jakarta.validation.Valid;

public interface UserService {
	
	public Users createUsers(QuitQRegistrationDto quitQRegistrationDto) throws ResourceNotFoundException;
	public List<Users> getUsers();
	public Users getUserById(UUID uId) throws ResourceNotFoundException;
    public Users updateUser(UserDto userDto,UUID uId) throws ResourceNotFoundException;
    public String deleteUser(UUID uId) throws ResourceNotFoundException;
    public Users updateUsersTier(UUID uid,Tiers tier) throws ResourceNotFoundException;
	public Tiers getUserTier(@Valid UserPartnerDto userPartnerDto);
	public List<Users> getUsersByPartner(UUID partnerId);
}
