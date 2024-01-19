package com.security.outh.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.security.oauth.configure.UserInfoUserDetails;
import com.security.oauth.entity.UserInfo;
import com.security.oauth.repository.UserInfoRepository;

public class UserInfoUserDetailsService implements UserDetailsService {

	@Autowired
	private UserInfoRepository repository;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<UserInfo> userInfo=repository.findByName(username);
		return userInfo.map(UserInfoUserDetails::new).
				orElseThrow(()->new UsernameNotFoundException("user NotFound "+username));
	}

}
