package com.config;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.constants.Constants;
import com.flash.domain.Profile;
import com.flash.domain.User;
import com.exception.UnauthorizedException;
import com.flash.repository.UserRepository;
import com.flash.security.FlashUserDetails;
import com.flash.service.AuthenticationService;
import com.flash.service.EmailService;

/**
 * <p>
 * Handles the user's access token.
 * </p>
 */
@Component
public class FlashTokenEnhancer implements TokenEnhancer {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private PropConfig propConfig;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Enhance the user's access token with his id.
	 * 
	 * If user's TFA is enabled, access token will not return until user passes the
	 * all the challenges. Google Authenticator implements two-step verification
	 * services using the Time-based One-time Password Algorithm (TOTP) and
	 * HMAC-based One-time Password Algorithm (HOTP), for authenticating users of
	 * mobile applications by Google. The service implements algorithms specified in
	 * RFC 6238 and RFC 4226.
	 *
	 * @param accessToken
	 *            the user's access token
	 * @param authentication
	 *            the user's access authentication
	 * @return OAuth2AccessToken with the user's access token
	 */
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		logger.info("Token Enhancing starts.");
		DefaultOAuth2AccessToken result = new DefaultOAuth2AccessToken(accessToken);
		Long userId = 0L;
		try {
			userId = ((FlashUserDetails) authentication.getUserAuthentication().getPrincipal()).getId();
		} catch (NullPointerException e) {
			logger.error("Authentication is null.");
			throw new UnauthorizedException("Authentication is null.");
		} catch (Exception e) {
			logger.error("Authentication error.", e);
			throw new UnauthorizedException("Authentication error.");
		}
		User user = userRepository.findOne(userId);
		Profile profile = user.getProfile();
		Map<String, Object> additionalInfo = new HashMap<String, Object>();
		additionalInfo.put(Constants.USER_ID, (Object) userId);
		additionalInfo.put(Constants.UUID, (Object) request.getHeader(Constants.UUID));
		additionalInfo.put(Constants.USER_ROLE, user.getRole().getName());
		additionalInfo.put(Constants.MEMBERSHIP, user.getMembershipList());
		additionalInfo.put(Constants.USER_EMAIL, user.getEmail());
		additionalInfo.put(Constants.USER_NAME, profile.getFullName());
		additionalInfo.put(Constants.USER_ROLE_ID, user.getRole().getRoleId());
		result.setAdditionalInformation(Collections.singletonMap(Constants.ADDITIONAL_INFO, (Object) additionalInfo));

		if (user.isTFA()
				&& !authentication.getOAuth2Request().getClientId().equals(propConfig.getMobileAppClientId())) {
			if (user.getGauthKey() == null || user.getGauthSecret() == null
					|| (request.getHeader(Constants.RESET_QR) != null
							&& Boolean.parseBoolean(request.getHeader(Constants.RESET_QR)))) {
				String gAuthUrl = authenticationService.getQRCode(user);
				if (Boolean.parseBoolean(request.getHeader(Constants.RESET_QR))) {
					emailService.sendResetQRRequest(user);
					result.setAdditionalInformation(Collections.singletonMap(Constants.MESSAGE,
							"Email sent to admin for reset QR-Code request."));
				} else {
					result.setAdditionalInformation(Collections.singletonMap(Constants.GAUTH_URL, gAuthUrl));
				}
				result = resetAccesToken(result);
			} else {
				if (request.getHeader(Constants.OTP) != null) {
					if (authenticationService.isOTPValid(Integer.parseInt(request.getHeader(Constants.OTP)),
							user.getGauthSecret())) {
						return result;
					} else {
						logger.error("Error! Please enter correct OTP.");
						throw new UnauthorizedException("Error! Please enter correct OTP.");
					}
				} else {
					result.setAdditionalInformation(Collections.singletonMap(Constants.OTP_SCREEN, true));
					result = resetAccesToken(result);
				}
			}
		}
		
		return result;
	}

	/**
	 * Reset the user's access token to an empty string and setting its expire time
	 * to the current date-time
	 * 
	 * @param result
	 *            DefaultOAuth2AccessToken instance
	 * 
	 * @return DefaultOAuth2AccessToken instance
	 */
	private DefaultOAuth2AccessToken resetAccesToken(DefaultOAuth2AccessToken result) {
		result.setValue("");
		result.setExpiration(new Date());
		return result;
	}

}