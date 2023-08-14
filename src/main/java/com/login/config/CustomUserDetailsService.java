package com.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.login.model.User;
import com.login.repo.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService
{

	@Autowired 
    private UserRepository userRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		User user=userRepo.findByUsername(username);
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		
		if(user==null)
		{
			throw new UsernameNotFoundException("User not found");
		}else
		{
			CustomUserDetails customUserDetails=new CustomUserDetails(user);
		    return customUserDetails;
		}
		}

}
