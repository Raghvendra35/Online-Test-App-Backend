package com.login.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.helper.UserFoundException;
import com.login.model.Role;
import com.login.model.User;
import com.login.model.UserRole;
import com.login.repo.UserRepository;
import com.login.service.UserService;



@RestController
@RequestMapping("api/")
@CrossOrigin(origins ="http://localhost:4200")
public class UserController
{

	
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
    @Autowired
	private BCryptPasswordEncoder bcryptPassordEncoder;
		
	
	
	
	
	//Create User
	@PostMapping("/user")
	public User createUser(@RequestBody User user) throws Exception 
	{
		//Set Roles and Save User
		
	 
		Set<UserRole> roles=new HashSet<>();
	    Role role=new Role();
       // role.setRoleId(32L);
        role.setRoleName("Normal");
        user.setPassword(bcryptPassordEncoder.encode(user.getPassword()));
        UserRole userRole=new UserRole();
         
        userRole.setUser(user);
        userRole.setRole(role);
                
	    roles.add(userRole);
	   
	      // User user2=this.userService.createUser(user, roles);
	       return this.userService.createUser(user, roles);
	    

	}

	
	@GetMapping("/username/{username}")
	public User getUser(@PathVariable("username") String username)
	{
		System.out.println("dgdg............");
	   return this.userService.getUser(username);
	}
	
	
	//delete user by Id
	@DeleteMapping("/delete/{userId}")
	public void deleteUser(@PathVariable Long userId)
	{
		this.userService.deleteUser(userId);
	
	
	
}
}
