package com.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

/**
 * <p>
 * A resource class for configuring the url authorization requests
 * </p>
 * 
 * @author Wittybrains
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private TokenEnhancer tokenEnhancer;
	private DefaultTokenServices defaultTokenServices;

	@Autowired
	public ResourceServerConfig(TokenEnhancer tokenEnhancer, DefaultTokenServices defaultTokenServices) {
		this.tokenEnhancer = tokenEnhancer;
		this.defaultTokenServices = defaultTokenServices;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/**/oauth/token").permitAll()
		.antMatchers("/**/test/**").permitAll()
		.antMatchers("/**/password/recovery-request").permitAll()
		.antMatchers("/**/password/recover").permitAll()
		.antMatchers("/**/password/recovery-link/validate").permitAll()
		.antMatchers("/**/user/qr-code/reset").permitAll()
		.antMatchers("/**/users/**").permitAll()
		.anyRequest().authenticated();
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		defaultTokenServices.setTokenEnhancer(tokenEnhancer);
		defaultTokenServices.setSupportRefreshToken(false);
		resources.tokenServices(defaultTokenServices).resourceId("com.entity");
	}
}
