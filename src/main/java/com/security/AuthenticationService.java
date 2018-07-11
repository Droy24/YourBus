package com.security;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import com.config.PropConfig;
import com.entity.User;
import com.repository.UserRepository;;


/**
 * A service class implementation for Authentication Controller
 *
 */
@Service
public class AuthenticationService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

//	private static GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();

	private UserRepository userRepository;
	private TokenStore tokenStore;
	private DefaultTokenServices tokenServices;
	private PropConfig propConfig;

	@Autowired
	public AuthenticationService(UserRepository userRepository, TokenStore tokenStore,
			DefaultTokenServices tokenServices, PropConfig propConfig) {
		this.userRepository = userRepository;
		this.tokenStore = tokenStore;
		this.tokenServices = tokenServices;
		this.propConfig = propConfig;
	}

	/**
	 * Method to get authenticated user
	 * 
	 * @return the authenticated User
	 */
	public User getAuthenticatedUser() {
		logger.info("To get the authenticated user.");
		FlashUserDetails zeusUserDetails;
		try {
			zeusUserDetails = (FlashUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			logger.error("Unauthorized Access.", e.getMessage());
			throw new UnauthorizedUserException("Unauthorized Access.");
		}
		User user = userRepository.findById(zeusUserDetails.getId()).get();
		
//		if (!user.isActive()) {
//			logger.error("This user no longer exists in our system.");
//			throw new UnauthorizedUserException("This user no longer exists in our system.");
//		}
		logger.info("Returning the authenticated user.");
		return user;
	}

	/**
	 * Method to get the QRCode string
	 * 
	 * @param user
	 *            the user
	 * 
	 * @return the QRCode string
	 */
//	public String getQRCode(User user) {
//		logger.info("To get the QR code for user : " + user.getEmail());
//		final GoogleAuthenticatorKey key = googleAuthenticator.createCredentials();
//		final String secret = key.getKey();
//		user.setGauthKey(key.toString());
//		user.setGauthSecret(secret);
//		userRepository.save(user);
//		logger.info("Returning the QR code for user : " + user.getEmail());
//		return GoogleAuthenticatorQRGenerator.getOtpAuthURL(user.getProfile().getFirstName(), user.getEmail(), key);
//	}

	/**
	 * Method to check if OTP is valid or not
	 * 
	 * @param otp
	 *            the otp
	 * 
	 * @param secret
	 *            the secret
	 * 
	 * @return true if OTP is valid or false if OTP is invalid
	 */
//	public boolean isOTPValid(Integer otp, String secret) {
//		logger.info("To check if OTP is Valid or not.");
//		return otp.equals(googleAuthenticator.getTotpPassword(secret));
//	}

	/**
	 * Method to revoke the access-token
	 * 
	 */
	public void revokeToken() {
		logger.info("To revoke Token.");
		OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) SecurityContextHolder.getContext()
				.getAuthentication();
		OAuth2AccessToken token = tokenStore.getAccessToken(oAuth2Authentication);
		tokenStore.removeAccessToken(token);
		logger.info("Returning after revoking Token.");
	}

	/**
	 * Method to revoke access-token
	 * 
	 * @param token
	 *            the token
	 */
	public void revokeToken(String token) {
		logger.info("To revoke Token : " + token);
		tokenServices.revokeToken(token);
		logger.info("Returning after revoking Token : " + token);
	}

	/**
	 * Method to revoke the access-token
	 * 
	 * @param request
	 *            the request
	 */
	public void revokeToken(HttpServletRequest request) {
		logger.info("To Revoke Token.");
		String authHeader = request.getHeader("Authorization");
		String tokenValue = authHeader.replace("Bearer", "").trim();
		revokeToken(tokenValue);
		logger.info("Returning after revoking Token.");
	}

	/**
	 * To revoke all the tokens of logged-in user.
	 * 
	 * @param request
	 *            the HttpServletRequest object.
	 */
//	public void revokeAllTokens(HttpServletRequest request, String userName) {
//		logger.info("To revoke all the tokens of logged-in user.");
//		List<OAuth2AccessToken> tokens = new ArrayList<OAuth2AccessToken>(tokenStore.findTokensByClientIdAndUserName(
//				propConfig.getWebAppClientId(), userName));
//		tokens.addAll(tokenStore.findTokensByClientIdAndUserName(propConfig.getMobileAppClientId(), userName));
//		tokens.stream().forEach(
//				token -> {
//					request.setAttribute(Constants.UUID,
//							((Map<String, Object>) token.getAdditionalInformation().get(Constants.ADDITIONAL_INFO))
//									.get(Constants.UUID));
//					tokenStore.removeAccessToken(token);
//					request.removeAttribute(Constants.UUID);
//				});
//		logger.info("Returning after revoking all the tokens of logged-in user.");
//	}

	/**
	 * To check whether a user is QCMangaer or not.
	 * 
	 * @param user
	 *            the user.
	 * 
	 * @return boolean.
	 */
//	public boolean isQCManager(User user) {
//		logger.info("To check whether a user is QCMangaer or not.");
//		return user.hasRole(RoleName.QC_Manager);
//	}
//
//	/**
//	 * To check whether a user is QCSupervisor or not.
//	 * 
//	 * @param user
//	 *            the user.
//	 * 
//	 * @return boolean.
//	 */
//	public boolean isQCSupervisor(User user) {
//		logger.info("To check whether a user is QCSupervisor or not.");
//		return user.hasRole(RoleName.QC_Supervisor);
//	}
//
//	/**
//	 * To check whether a user is QCSupervisor or not.
//	 * 
//	 * @param user
//	 *            the user.
//	 * 
//	 * @return boolean.
//	 */
//	public boolean isQCOperator(User user) {
//		logger.info("To check whether a user is QCSupervisor or not.");
//		return user.hasRole(RoleName.QC_Operator);
//	}
//
//	/**
//	 * To validate whether user is QcManager or QcSupervisor.
//	 * 
//	 * @param user
//	 *            the user.
//	 */
//	public void validateQCManagerOrQCSupervisor(User user) {
//		logger.info("To validate whether user is QcManager or QcSupervisor.");
//		if (!(isQCManager(user) || isQCSupervisor(user))) {
//			logger.info("Info about To check whether a user is QCSupervisor or not.");
//			throw new UnauthorizedException("Unauthorized Access.");
//		}
//	}
//
//	/**
//	 * To validate whether QC-user is QcManager or QcSupervisor or QcOperator.
//	 * 
//	 * @param user
//	 *            the user.
//	 */
//	public void validateQCUser(User user) {
//		logger.info("To validate whether QC-user is QcManager or QcSupervisor or QcOperator.s");
//		if (!(isQCManager(user) || isQCSupervisor(user) || isQCOperator(user))) {
//			logger.info("Info about To check whether a user is QCSupervisor or not.");
//			throw new UnauthorizedException("Unauthorized Access.");
//		}
//	}
//
//	/**
//	 * To check whether a user is SysAdmin or not.
//	 * 
//	 * @param user
//	 *            the user.
//	 */
//	public void validateUserSystemAdmin(User user) {
//		logger.info("To check whether a user is SysAdmin or not.");
//		if (!user.hasRole(RoleName.Admin)) {
//			logger.info("Info about To check whether a user is QCSupervisor or not.");
//			throw new UnauthorizedException("Unauthorized Access!!");
//		}
//	}
//
//	/**
//	 * To check person does belongs to QC Team.
//	 * 
//	 * @param user
//	 *            the user
//	 * 
//	 * @return boolean true if person belongs to QC team.
//	 */
//	public boolean isQCTeam(User user) {
//		logger.info("To check person does belongs to QC Team.");
//		boolean isQCTeamPerson = user.hasRole(RoleName.QC_Manager) || user.hasRole(RoleName.QC_Operator)
//				|| user.hasRole(RoleName.QC_Supervisor);
//		return isQCTeamPerson;
//	}
//
//	/**
//	 * To validate Media manager user.
//	 * 
//	 * @param user
//	 *            the user.
//	 */
//	public void validMediaManagerUser(User user) {
//		logger.info("To validate Media manager user.");
//		Role role = user.getRole();
//		UserType userType = role.getUserType();
//		if (!(userType.getName().equals(UserTypeName.Customer) || userType.getName().equals(UserTypeName.Organisation) || userType
//				.getName().equals(UserTypeName.Admin))) {
//			logger.info("Unauthorized exception!!");
//			throw new UnauthorizedException("Unauthorized Access!!");
//		}
//		switch (userType.getName()) {
//		case Customer:
//			Customer customer = user.getCustomerUser().getCustomer();
//			if (!customer.getMembershipList().contains(MembershipName.Flash_Media.getMembershipNameString())) {
//				logger.info("Unauthorized exception!!");
//				throw new UnauthorizedException("Unauthorized Access!!");
//			}
//			break;
//		case Organisation:
//			Organisation organisation = user.getOrganisationUser().getOrganisation();
//			if (!organisation.getMembershipList().contains(MembershipName.Flash_Media.getMembershipNameString())) {
//				logger.info("Unauthorized exception!!");
//				throw new UnauthorizedException("Unauthorized Access!!");
//			}
//			break;
//		case Admin:
//			break;
//		default:
//			throw new UnauthorizedException("Unauthorized Access!!");
//		}
//		logger.info("Returning after validating Media manager user.");
//	}
//
//	/**
//	 * To validate whether user is QcManager.
//	 * 
//	 * @param user
//	 *            the user.
//	 */
//	public void validateQCManager() {
//		logger.info("To validate whether user is QcManager.");
//		if (!(isQCManager(getAuthenticatedUser()))) {
//			logger.info("Info about To check whether a user is QcManager or not.");
//			throw new UnauthorizedException("Unauthorized Access.");
//		}
//	}
}