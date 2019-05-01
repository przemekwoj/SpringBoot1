package com.przemo.RestAPI.repository.parent;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.przemo.RestAPI.entity.grade.Grade;
import com.przemo.RestAPI.entity.parent.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	//@Query("SELECT user FROM User user WHERE user.lastname = :lastname AND user.password = :password")
	//Optional<User> findParticularUser(@Param("lastname") String lastname,@Param("password") String password); 
	
	Optional<User> findByUsername(String username);
}
