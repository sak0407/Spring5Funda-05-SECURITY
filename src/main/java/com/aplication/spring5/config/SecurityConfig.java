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
		auth.inMemoryAuthentication().withUser("mary").password("{noop}test123").roles("EMPLOYEE","MANAGER");
		auth.inMemoryAuthentication().withUser("adam").password("{noop}test123").roles("EMPLOYEE","ADMIN");
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			//.anyRequest().authenticated()
		.antMatchers("/").hasRole("EMPLOYEE")
		.antMatchers("/leaders/**").hasRole("MANAGER")
		.antMatchers("/systems/**").hasRole("ADMIN")
			.and()
			.formLogin()
				.loginPage("/loginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll()
			.and()
				.logout()
				.permitAll()
				.and()
				.exceptionHandling().accessDeniedPage("/access-denied");
				
				
	}
	
	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.authorizeRequests()
	 *  .antMatchers("/").permitAll() // allow public access to home page .antMatchers("/employees").hasRole("EMPLOYEE")
	 * .antMatchers("/leaders/**").hasRole("MANAGER")
	 * .antMatchers("/systems/**").hasRole("ADMIN")
	 *  .and()
	 *   .formLogin()
	 * .loginPage("/showMyLoginPage") .loginProcessingUrl("/authenticateTheUser")
	 * .permitAll() 
	 * .and()
	 *  .logout()
	 *   .logoutSuccessUrl("/") // after logout then
	 * redirect to landing page (root) .permitAll(); }
	 */

}


























