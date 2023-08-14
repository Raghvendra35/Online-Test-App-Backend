package com.login.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.login.service.UserService;
import com.login.helper.UserFoundException;
import com.login.model.User;
import com.login.model.UserRole;
import com.login.repo.RoleRepository;
import com.login.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService 
{

	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	

	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//Save User we used we generated token  
	public User save(User user)
	{
	
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setUserRole("Normal");;
		User savedUser=userRepository.save(user);
		return savedUser;
	}
	
	
	
	//Creating user
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception
	{
	User local=this.userRepository.findByUsername(user.getUsername());
	if(local!=null)
	{
		System.out.print("User isUser already there !!!");
		throw new UserFoundException(); 
	}else
	{
		//create user
		for(UserRole ur: userRoles)
		{
			//this.roleRepository.save(ur.getRole());
		}
		
		//user.setPassword(passwordEncoder.encode(user.getPassword()));
		System.out.println("Priting pass.................");
		System.out.println(user.getPassword());
	    //user.getUserRoles().addAll(userRoles);
	 local=this.userRepository.save(user);
	}
		return local;
	}

	
	

	//Getting user by username
	@Override
	public User getUser(String username) 
	{
		return this.userRepository.findByUsername(username);
	}


	@Override
	public void deleteUser(Long userId)
	{
		//this.userRepository.deleteById(userId);
		
	}
}
