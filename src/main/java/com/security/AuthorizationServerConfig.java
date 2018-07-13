package com.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

import com.config.PropConfig;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private AuthenticationManager authenticationManager;
	private DefaultTokenServices defaultTokenServices;
	private PropConfig propConfig;

	@Autowired
	public AuthorizationServerConfig(AuthenticationManager authenticationManager,
			DefaultTokenServices defaultTokenServices, PropConfig propConfig) {
		this.authenticationManager = authenticationManager;
		this.defaultTokenServices = defaultTokenServices;
		this.propConfig = propConfig;
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient(propConfig.getWebAppClientId()).secret(propConfig.getWebAppApiSecret())
				.authorities("CLIENT", "TRUSTED_CLIENT", "ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
				.scopes("read", "write", "trust")
//				.authorizedGrantTypes("password", "authorization_code", "implicit", "client_credentials");
				.authorizedGrantTypes("authorization_code", "refresh_token", "password");
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenServices(defaultTokenServices).authenticationManager(authenticationManager);
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.checkTokenAccess("hasAuthority('SERVICE')");
	}
}