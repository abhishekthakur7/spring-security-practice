package com.security.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("admin")
			.password(passwordEncoder().encode("admin")) //password need to be encoded before storing in memory/database
			.roles("ADMIN")
			.and()
			.withUser("user")
			.password(passwordEncoder().encode("user"))
			.roles("USER");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests() //authorize requests
				//order of antMatchers is very important
				.antMatchers("/").permitAll() //allow access to all for "/"
				.antMatchers("/admin").hasRole("ADMIN") //allow access to only ADMIN role to "/admin"
				.antMatchers("/user").hasRole("USER") //allow access to only ADMIN role to "/admin"
				.and()
				.httpBasic(); //basic http authentication
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}