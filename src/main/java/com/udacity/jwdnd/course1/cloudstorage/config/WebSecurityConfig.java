package com.udacity.jwdnd.course1.cloudstorage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private AuthenticationService authenticationService;

	public WebSecurityConfig(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(this.authenticationService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/signup", "/css/**", "/js/**").permitAll()
				.anyRequest()
				.authenticated();

		http.formLogin()
				.loginPage("/login")
				.permitAll()
				.defaultSuccessUrl("/home", true);

		http.logout().logoutUrl("/logout")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.logoutSuccessUrl("/login?logout").permitAll();
	}
	
	@Override
	public void configure(WebSecurity webSecurity) {
		webSecurity.ignoring().antMatchers("/h2-console/**");
	}

}
