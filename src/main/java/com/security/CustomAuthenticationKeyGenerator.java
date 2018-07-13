package com.security;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;

import com.config.Constants;


/**
 * This is a custom authentication key generator. This will create access token
 * by taking account into the "Scope", "UserName", "Client_ID" and "UUID". We
 * added support for UUID check as well to generate unique key per unique
 * device.
 * @author Avinash
 *
 */

public class CustomAuthenticationKeyGenerator implements AuthenticationKeyGenerator {

	@Autowired
	private HttpServletRequest request;

	// @Autowired
	// @Lazy
	// TokenStore tokenStore;
	
	private static final String CLIENT_ID = "client_id";

	private static final String SCOPE = "scope";

	private static final String USERNAME = "username";

	private static final String UUID_KEY = "uuid";

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public String extractKey(OAuth2Authentication authentication) {
		
		// OAuth2Authentication authentication founded null if token already expired.
		if (authentication == null) {
			return "";
		}
		
		// String userName = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" :
		// authentication.getName();
		// ArrayList<OAuth2AccessToken> tokens = new ArrayList<OAuth2AccessToken>(
		// tokenStore.findTokensByClientIdAndUserName(propConfig.getWebAppClientId(),
		// userName));

		//// Collection<OAuth2AccessToken> tokens =
		//// tokenStore.findTokensByClientId(authentication.getOAuth2Request().getClientId());
		//
		// // tokens.stream().forEach(token -> System.out.println(token.getValue()));
		//// ArrayList<OAuth2AccessToken> list = new ArrayList<OAuth2AccessToken>();
		//// tokens.stream().forEach(token -> list.add(token));
		//
		// tokens.stream().filter(t -> t.isExpired()).forEach(token -> {
		// token.getValue();
		// request.setAttribute(Constants.UUID,
		// ((Map<String, Object>)
		//// token.getAdditionalInformation().get(Constants.ADDITIONAL_INFO))
		// .get(Constants.UUID));
		// tokenStore.removeAccessToken(token);
		// request.removeAttribute(Constants.UUID);
		// });
		//
		//// DefaultTokenServices tokenServices = beanConfig.defaultTokenServices();
		//// TokenStore tokenStore = beanConfig.tokenStore();
		//
		//// OAuth2AccessToken oAuth2AccessToken =
		//// defaultTokenServices.getAccessToken(authentication);
		//// if (oAuth2AccessToken.isExpired()) {
		//// System.out.println("---------------------------------------***** TOKEN
		//// EXPIRED *****----------------------------");
		//// System.out.println("Access Token is = " + oAuth2AccessToken.getValue());
		//// defaultTokenServices.revokeToken(oAuth2AccessToken.getValue());
		//// System.out.println("---------------------------------------***** TOKEN
		//// REMOVED *****----------------------------");
		//// }

		logger.info("CustomAuthenticationKeyGenerator :: extractKey() method starts.");
		try {
			Map<String, String> values = new LinkedHashMap<String, String>();
			OAuth2Request authorizationRequest = null;
			try {
				authorizationRequest = authentication.getOAuth2Request();
			} catch (Exception ex) {
				logger.error("Error while getting OAuth request.Authentication = " + authentication
						+ " authentication.getOAuth2Request( )= " + authentication.getOAuth2Request(), ex);
				throw new Exception("Error while getting OAuth request.");
			}
			if (!authentication.isClientOnly()) {
				values.put(USERNAME, authentication.getName());
			}
			values.put(CLIENT_ID, authorizationRequest.getClientId());
			if (authorizationRequest.getScope() != null) {
				values.put(SCOPE, OAuth2Utils.formatParameterList(authorizationRequest.getScope()));
			}
			/*
			 * Map<String, Serializable> extentions = authorizationRequest.getExtensions();
			 * String uuid = null; if (extentions == null) { extentions = new
			 * HashMap<String, Serializable>(1); uuid = UUID.randomUUID().toString();
			 * extentions.put(UUID_KEY, uuid); } else { uuid = (String)
			 * extentions.get(UUID_KEY); if (uuid == null) { uuid =
			 * UUID.randomUUID().toString(); extentions.put(UUID_KEY, uuid); } }
			 */
			if (isLoginRequest(request) || isLogoutRequest(request) || isPasswordRequest(request)) {
				String uuid = null;
				if (isPasswordRequest(request)) {
					uuid = (String) request.getAttribute(Constants.UUID);
				} else {
					uuid = request.getHeader("uuid");
				}
				if (uuid == null) {
					logger.error("Fatal Error. UUID is missing!");
					throw new IllegalStateException("Fatal Error. UUID is missing!");
				}
				values.put(UUID_KEY, uuid);
			}

			MessageDigest digest;
			try {
				digest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				logger.error("MD5 algorithm not available.  Fatal (should be in the JDK).", e);
				throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).");
			}

			try {
				byte[] bytes = digest.digest(values.toString().getBytes("UTF-8"));
				return String.format("%032x", new BigInteger(1, bytes));
			} catch (UnsupportedEncodingException e) {
				logger.error("UTF-8 encoding not available.  Fatal (should be in the JDK).", e);
				throw new IllegalStateException("UTF-8 encoding not available.  Fatal (should be in the JDK).");
			}
		} catch (Exception e) {
			logger.error("Error while extracting key.", e);
			throw new IllegalStateException("Error while login.");
		}
	}

	private boolean isLoginRequest(HttpServletRequest request) {
		String s = request.getRequestURL().toString();
		Pattern pattern = Pattern.compile(".*/oauth/token.*");
		Matcher matcher = pattern.matcher(s);
		return matcher.matches();
	}

	private boolean isLogoutRequest(HttpServletRequest request) {
		String s = request.getRequestURL().toString();
		Pattern pattern = Pattern.compile(".*/logout.*");
		Matcher matcher = pattern.matcher(s);
		return matcher.matches();
	}

	private boolean isPasswordRequest(HttpServletRequest request) {
		String s = request.getRequestURL().toString();
		Pattern pattern = Pattern.compile(".*/password.*");
		Matcher matcher = pattern.matcher(s);
		return matcher.matches();
	}

}