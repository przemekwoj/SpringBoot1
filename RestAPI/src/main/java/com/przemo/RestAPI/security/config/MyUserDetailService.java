package com.przemo.RestAPI.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.przemo.RestAPI.entity.parent.User;
import com.przemo.RestAPI.repository.parent.UserRepository;
import com.przemo.RestAPI.repository.service.UserService;

@Service
public class MyUserDetailService implements UserDetailsService
{

	@Autowired   
	UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("not found username"));
        return new UserPrincipal(user);
	}

}
