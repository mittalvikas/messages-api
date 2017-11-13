package com.mittalvm.spring.messages.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.mittalvm.spring.messages.service.MessageUserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationEntryPoint authEntryPoint;
	
	private MessageUserService messageUserService;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable().authorizeRequests()
		.anyRequest().authenticated()
		.and().authorizeRequests().antMatchers("/console/**").permitAll().and().httpBasic()
		.authenticationEntryPoint(authEntryPoint);
    	http.headers().frameOptions().disable();
    	
    	/*http.authorizeRequests().antMatchers("/").permitAll().and()
        .authorizeRequests().antMatchers("/console/**").permitAll();
    	http.csrf().disable();
    	http.headers().frameOptions().disable();*/
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
           /* .userDetailsService(messageUserService);*/
            .inMemoryAuthentication()
                .withUser("vikas.mittal").password("Welcome1").roles("USER");
    }
}