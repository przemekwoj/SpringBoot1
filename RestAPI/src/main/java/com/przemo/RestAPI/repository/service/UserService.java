package com.przemo.RestAPI.repository.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.przemo.RestAPI.entity.parent.User;
import com.przemo.RestAPI.entity.user.Student;

public interface UserService 
{
	public Optional<User> findByUsername(String username);
	
	public List<User> getAllUsers();
	
	public Optional<User> getUserById(int id);
	
	public User saveUser(User user);
	
	public User updateUser(User user);
	
	public void deleteUser(int id);


}
