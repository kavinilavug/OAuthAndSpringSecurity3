package com.security.oauth.configure;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.security.oauth.entity.UserInfo;

@Component
public class UserInfoUserDetails implements UserDetails{
	private String name;
	private String password;
	private List<GrantedAuthority> authorities;
	
     public UserInfoUserDetails(UserInfo userInfo) {
    	 name=userInfo.getName();
    	 password=userInfo.getPassword();
    	 authorities=Arrays.stream(UserInfo.getRoles().split(","))
    			 .map(SimpleGrantedAuthority::new)
    			 .collect(Collectors.toList());
     }

	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
