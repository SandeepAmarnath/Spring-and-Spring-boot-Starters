package com.mycode.springbootsecuritydemo.initializer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mycode.springbootsecuritydemo.service.UserService;


@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	// add a reference to our security data source
    @Autowired
    private UserService userService;
	
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    
   @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/welcome/").permitAll()
			.antMatchers("/employees/**").hasRole("EMPLOYEE")
			.antMatchers("/leaders/**").hasRole("MANAGER")
			.antMatchers("/systems/**").hasRole("ADMIN")
			.and()
			.formLogin()
				.loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.successHandler(customAuthenticationSuccessHandler)
				.permitAll()
			.and()
			.logout().permitAll();
		
	}
	
	
//	antMatchers("/").permitAll()
//
//	.antMatchers("/employees").hasRole("EMPLOYEE")
//
//	.antMatchers("/leaders/**").hasRole("MANAGER")
//
//	.antMatchers("/systems/**").hasRole("ADMIN")
//
//	.and()
//
//	.formLogin()
//
//	.loginPage("/showMyLoginPage")
//
//	.loginProcessingUrl("/authenticateTheUser")
//
//	.permitAll()
//
//	.and()
//
//	.logout().permitAll();
//
//	}
	
	//beans
	//bcrypt bean definition
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//authenticationProvider bean definition
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService); //set the custom user details service
		auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
		return auth;
	}
	  
}
