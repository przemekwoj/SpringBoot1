package com.przemo.RestAPI.repository.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.przemo.RestAPI.entity.parent.User;
import com.przemo.RestAPI.repository.parent.UserRepository;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<User> findByUsername(String username) 
	{
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public Optional<User> getUserById(int id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(int id) {
		userRepository.deleteById(id);		
	}
}
