package com.login.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.login.model.UserRole;
import com.login.model.User;
import com.login.repo.UserRepository;

@Service
public interface UserService
{

	
	
	
	
	
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	@Autowired
//	private UserRepository userRepo;
//	//Save User
//	public User save(User user)
//	{
//	
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
//		User savedUser=userRepo.save(user);
//		return savedUser;
//	}
//	
	
	//Create User
	//Set role of user
	public User createUser(User user, Set<UserRole> userRoles) throws Exception;

	//Get user by username
	public User getUser(String username);
	
	//Delete user by Id
	public void deleteUser(Long userId); 
	
}
