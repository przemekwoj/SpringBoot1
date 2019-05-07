package com.przemo.RestAPI.security.config;


import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.przemo.RestAPI")
@EnableJpaRepositories(basePackages = "com.przemo.RestAPI")
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {
 
	@Autowired
	private UserDetailsService userDetailsService;



	@Bean 
	public AuthenticationProvider authProvider()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		
		return provider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.authenticationProvider(authProvider());
	}
	

	
	
	protected void configure(HttpSecurity http) throws Exception {
	    http
	        .authorizeRequests()
            	.antMatchers(HttpMethod.GET,"/students/**").hasRole("Student")
	            .antMatchers(HttpMethod.POST,"/students/**").hasRole("Teacher")
	            .antMatchers(HttpMethod.PUT,"/students/**").hasRole("Teacher")
	            .antMatchers(HttpMethod.DELETE,"/students/**").hasRole("Admin")
	            .anyRequest().authenticated()  
	            .and()
	        .formLogin().permitAll()
	            .and()
	            .logout() .logoutUrl("/logout")  
	            .and()
	        .httpBasic()
	        //csrf need to be disable when we use rest client application , when we use browser we can enable csrf
	        .and()
	        .csrf().disable();
	}
	

	
}