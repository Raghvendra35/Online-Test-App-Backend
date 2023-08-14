package com.login.config;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.login.model.Authority;
import com.login.model.User;
import com.login.model.UserRole;

@Component
public class CustomUserDetails implements UserDetails
{

	User user;
	
	private Set<UserRole> userRoles=new HashSet<>();
	
	
	public CustomUserDetails() {
	//	super();
		
	}

	public CustomUserDetails(User user)
	{
		 this.user=user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() 
	{
       Set<Authority> set=new HashSet<>();
 	  //here we are finding role one by one 
       this.userRoles.forEach(userrole->{
    	 //here we are returning
    	 //  set.add(new Authority(userrole.getRole().getRoleName()));
       });
       
		return set;
	}
	
	

	@Override
	public String getPassword() 
	{
		System.out.println(user.getPassword());
		return user.getPassword();
	}

	@Override
	public String getUsername()
	{
		System.out.println(user.getUsername());
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		return true;
	}

	
}
