package com.security.oauth.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.security.oauth.entity.UserInfo;
import com.security.oauth.model.Product;
import com.security.oauth.repository.UserInfoRepository;

@RestController
public class ProductContoller {
	
	@Autowired
	UserInfoRepository userInfoRepository;
	

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/welcome")
	String getProduct() {
		return "you can get the products";		
	}
	
	@GetMapping("/product/{id}")
	//enable method level security .only user role can access.nilavu-user
	@PreAuthorize("hasAuthority('ROLE_USER')")
	String getProduct(@PathVariable int id) {
		return "you can get the products";		
	}
	
	@GetMapping("/product")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	//enable method level security .only admin role can access.kavi -admin
	List<Product> getAllProduct() {
		List produts=new ArrayList();
		return produts;
		
	}
	
	@PostMapping("/product")
	String addNewUser(@RequestBody UserInfo userinfo)
	{
		userinfo.setPassword(passwordEncoder.encode(userinfo.getPassword()));
		userInfoRepository.save(userinfo);
		return "User added into system";
		
	}
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
