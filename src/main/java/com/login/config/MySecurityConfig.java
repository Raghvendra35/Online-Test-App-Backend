
package com.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class MySecurityConfig 
{
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter; 
	
	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
	{
		return httpSecurity
				            .csrf()
				           .disable()
				           .cors().disable()
				           .authorizeHttpRequests()
				           .requestMatchers("/generate-token").permitAll()
				           .requestMatchers("/adduser","/user").permitAll()
				           .requestMatchers("/user").permitAll()
				           .requestMatchers("/api/**").permitAll()
				           .requestMatchers(HttpMethod.GET).permitAll()				           
				           .anyRequest()
				           .authenticated()
				           .and()
				           .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				           .and()
				           .authenticationProvider(authenticationProvider())
				           .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).build();
				           

}

	 @Bean
	 public AuthenticationProvider authenticationProvider()
	 {
		DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider(); 
		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	 }
	 
	 @Bean
	 public UserDetailsService userDetailsService()
	 {
		return new CustomUserDetailsService(); 
	 }
	 
	 
	 @Bean
	 public BCryptPasswordEncoder passwordEncoder()
	 {
		 return new BCryptPasswordEncoder();
	 }
	 
	 
	 @Bean
	 public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception
	 {
		 return configuration.getAuthenticationManager();
	 }
}
	 
	 