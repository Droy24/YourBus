package com.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import com.config.PropConfig;

/**
 * <p>
 * BootStrap of all initially required configuration beans.
 * </p>
 * 
 * @author RKushwah
 * @since 1.0
 */
@Configuration
public class InitialBeanConfig {
	
	@Autowired
	PropConfig propConfig;

	/**
	 * Instantiate the Bean of CustomAuthenticationKeyGenerator to get a key.
	 * 
	 * @return CustomAuthenticationKeyGenerator the customAuthenticationKeyGenerator
	 *         instance
	 */
	@Bean
	public CustomAuthenticationKeyGenerator customAuthenticationKeyGenerator() {
		return new CustomAuthenticationKeyGenerator();
	}
	
	/**
	 * Instantiate the Bean of TokenStore to get an inMemory tokenStore.
	 * 
	 * @return TokenStore the inMemory tokenStore instance
	 */
	
	@Bean
	public TokenStore tokenStore() 
	{
		InMemoryTokenStore ts = new InMemoryTokenStore();
		ts.setAuthenticationKeyGenerator(customAuthenticationKeyGenerator());
		return ts;
	}

	/**
	 * Instantiate the Bean of TokenEnhancer to enhance a token.
	 * 
	 * @return TokenEnhancer instance
	 */
	
	@Bean
	public TokenEnhancer tokenEnhancer() {
		return new FlashTokenEnhancer();
	}

	/**
	 * Instantiate the Bean of DefaultTokenServices.
	 * 
	 * @return DefaultTokenServices instance
	 */
	@Primary
	@Bean
	public DefaultTokenServices defaultTokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setReuseRefreshToken(false);
		defaultTokenServices.setAccessTokenValiditySeconds(propConfig.getAccessTokenExpirySeconds());
		return defaultTokenServices;
	}

	/**
	 * Get the template loader resolver. Folder is set to: static/html/mail/
	 * 
	 * @return ClassLoaderTemplateResolver responsible to locate and resolve e-mail
	 *         templates
	 */
//	@Bean
//	public ClassLoaderTemplateResolver emailTemplateResolver() {
//		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//		templateResolver.setPrefix("static/html/mail/");
//		templateResolver.setTemplateMode("HTML5");
//		templateResolver.setCharacterEncoding("UTF-8");
//		templateResolver.setOrder(1);
//		return templateResolver;
//	}

	/**
	 * Get the e-mail template engine.
	 *
	 * @return TemplateEngine the e-mail template processor
	 */
//	@Bean
//	public TemplateEngine emailTemplateEngine() {
//		TemplateEngine engine = new TemplateEngine();
//		engine.setTemplateResolver(emailTemplateResolver());
//		return engine;
//	}

}
