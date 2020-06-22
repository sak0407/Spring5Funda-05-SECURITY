package com.aplication.spring5.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//add users for in memory authentication		
		UserBuilder users=User.withDefaultPasswordEncoder();
		
		auth.inMemoryAuthentication().withUser("john").password("{noop}test123").roles("EMPLOYEE");
		auth.inMemoryAuthentication().withUser("mary").password("{noop}test123").roles("MANAGER");
		auth.inMemoryAuthentication().withUser("adam").password("{noop}test123").roles("ADMIN");
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/loginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll()
			.and()
				.logout()
				.permitAll();
				
				
	}

}


























