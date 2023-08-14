 package com.login.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.config.CustomUserDetails;
import com.login.config.CustomUserDetailsService;
import com.login.config.JwtService;
import com.login.model.JwtRequest;
import com.login.model.JwtResponse;
import com.login.model.Role;
import com.login.model.User;
import com.login.repo.UserRepository;
import com.login.service.UserService;
import com.login.service.impl.UserServiceImpl;

@RestController
@RequestMapping("api/")
@CrossOrigin("*")
public class AuthenticateController
{

	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired 
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private UserRepository userRepo;
	
	
	
	
	//Create new User
	@PostMapping("/adduser")
	public ResponseEntity<User> addNewUser(@RequestBody User user)
	{
	 Role role=new Role();	
	     role.setRoleName("Normal");
	  // user.setRole((List<Role>) role);
     User saved=userServiceImpl.save(user);
	
     return new ResponseEntity<User>(saved, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	//Generate token at login time
	@PostMapping("generate-token")
	public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{
		
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
	
		System.out.println("Inside Generate token,..........");
		
		if(authenticate.isAuthenticated())
			{
				 String generateToken = jwtService.generateToken(jwtRequest.getUsername());
				 String username=jwtRequest.getUsername();
			//Get role of user which is login
				 String role="Normal";
				System.out.println("Printing token.........."); 	
				System.out.println(generateToken);
				 
				User user=this.userRepo.getUserByPassingUsername(username);
				 return new ResponseEntity<JwtResponse>((new JwtResponse(generateToken,role,username)),HttpStatus.OK);
			
			}else 
			   {
				 throw new UsernameNotFoundException("invalid user request !!!");
			   }
		   
	         }
	
	
	//Here Get Current User is Normal or Admin
	//Principal is a interface it has one method that's a loadUserByUsername()
	//this method will return userName then we can get UserRole on the basic of username
	//when this request will hit then this method will return all details which user is login (current)
	@GetMapping("/current-user")
	public CustomUserDetails getCurrentUser(Principal principal)
	{
		System.out.println("Principal..............................");
        System.out.println(this.customUserDetailsService.loadUserByUsername(principal.getName()));
        
        System.out.println("Principal .....................");
		return  (CustomUserDetails) this.customUserDetailsService.loadUserByUsername(principal.getName());
        //return null;
	}
	
}











