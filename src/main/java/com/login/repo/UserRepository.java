package com.login.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.login.model.User;

public interface UserRepository extends JpaRepository<User, Integer> 
{

	public User findByUsername(String username);

	@Query(value= "SELECT * FROM USER WHERE username=?", nativeQuery=true)
	public User getUserByPassingUsername(String username);
}
