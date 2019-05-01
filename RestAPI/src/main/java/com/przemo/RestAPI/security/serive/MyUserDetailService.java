package com.przemo.RestAPI.security.serive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.przemo.RestAPI.entity.parent.User;
import com.przemo.RestAPI.repository.parent.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService
{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("not found username"));
        return new UserPrincipal(user);
	}

}
