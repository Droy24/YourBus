package com.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.Md4PasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final CharSequence PBKDF2_2018_SECRET = "mypass@123";
	private static final int PBKDF2_2018_ITERATIONS = 5;
	private static final int PBKDF2_2018_HASH_WIDTH = 10;
	
	
	private FlashUserDetailsService flashUserDetailsService;

	@Autowired
	public SecurityConfig(@Lazy FlashUserDetailsService flashUserDetailsService) {
		this.flashUserDetailsService = flashUserDetailsService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().anyRequest().authenticated();
	}

	/**
	 * Instantiate the Bean of PasswordEncoder to encode the password.
	 * 
	 * @return BCryptPasswordEncoder instance of PasswordEncoder
	 */
	
//		return new BCryptPasswordEncoder(10);
//}
	/*
	  @Bean 
	public DelegatingPasswordEncoder passwordEncoder() {
		String currentId = "marketplace-app";

	    // List of all encoders we support. Old ones still need to be here for rolling updates
	    Map<String, PasswordEncoder> encoders = new HashMap<>();
	    
	    
	    encoders.put("ldap", new LdapShaPasswordEncoder());
	    encoders.put("MD4", new Md4PasswordEncoder());
	    encoders.put("MD5", new MessageDigestPasswordEncoder("MD5"));
	    encoders.put("noop", NoOpPasswordEncoder.getInstance());
	    encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
	    encoders.put("scrypt", new SCryptPasswordEncoder());
	    encoders.put("SHA-1", new MessageDigestPasswordEncoder("SHA-1"));
	    encoders.put("SHA-256", new MessageDigestPasswordEncoder("SHA-256"));
	    encoders.put("sha256", new StandardPasswordEncoder());
	    encoders.put("bcrypt", new BCryptPasswordEncoder());
	    encoders.put(currentId, new Pbkdf2PasswordEncoder(PBKDF2_2018_SECRET, PBKDF2_2018_ITERATIONS, PBKDF2_2018_HASH_WIDTH));

	    return new DelegatingPasswordEncoder("currentId", encoders);
		
//		return (DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();
		}
	//}
	*/	
		
	
@SuppressWarnings("deprecation")
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
	    return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}


//	@Bean
//	public static NoOpPasswordEncoder passwordEncoder() {
//	    return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(flashUserDetailsService)
		.passwordEncoder(passwordEncoder());
		
		
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
