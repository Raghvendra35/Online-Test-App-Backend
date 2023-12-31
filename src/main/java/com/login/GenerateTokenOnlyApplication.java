package com.login;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.login.model.User;
import com.login.model.UserRole;
import com.login.service.UserService;

@SpringBootApplication
public class GenerateTokenOnlyApplication {

	

	@Autowired
	private UserService userService;
	
	public static void main(String[] args)
	{
		SpringApplication.run(GenerateTokenOnlyApplication.class, args);
		
		System.out.println("Running .................");
	}

	//CommnadLineRunner is used to give the area to run code whatever we will write in run() method
		//when project will run on that time this method will run
		//@Override
		public void run(String... args) throws Exception
		{
			
			System.out.println("Starting code .......");
			User user=new User();
			//user.setFirstName("Raghav");
			//user.setLastName("Yadav");
			
			//user.setUsername("raghav234");
			//user.setPassword("123");
			//user.setEmail("raghav@gmail.com");
			//user.setProfile("default.png");
			
			//Role role1=new Role();
			//role1.setRoleId(44L);
			//role1.setRoleName("ADMIN");

			Set<UserRole> userRoleSet=new HashSet<>();
			
			UserRole userRole=new UserRole();
			//userRole.setRole(role1);
			userRole.setUser(user);
			
			userRoleSet.add(userRole);
			
			//User user1=this.userService.createUser(user, userRoleSet);
	       // System.out.println(user1);

			}
		
		
}