package com.security.oauth.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.security.outh.service.UserInfoUserDetailsService;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity//enabled method level security 
public class ProductSecurityConfig  {

	//spring 2.0
	//three overloaded method in websecurityconfigureAdapter	
	//Aunthentication(this is like Basic Aunthentication)
/*	@Bean
	public UserDetailsService userDeatailsService(PasswordEncoder encoder){
		UserDetails admin=User.withUsername("kavi")
				.password(encoder.encode("kavi"))
				.roles("ADMIN")
				.build();		
		UserDetails user=User.withUsername("nilavu")
		.password(encoder.encode("nilavu"))
		.roles("USER")
		.build();
		return new InMemoryUserDetailsManager(admin,user);		
	}
	//password encrypt
	@Bean 		
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}*/
	
	@Bean 
	public UserDetailsService userDeatailsService() {
		return new UserInfoUserDetailsService();		
	}
	
	
	//Authorization 
	public SecurityFilterChain securityFliterChain(HttpSecurity http) throws Exception {
		return	http.csrf().disable()
				.authorizeHttpRequests().requestMatchers("/welocome").permitAll()
		       .and()
		       .authorizeHttpRequests().requestMatchers("/product/**").authenticated().
		       and().formLogin().and().build();		
	}
	
}

