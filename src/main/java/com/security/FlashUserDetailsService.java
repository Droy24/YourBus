package com.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.config.Constants;
import com.entity.User;
import com.service.UserService;


/**
 * <p>
 * A core class which loads user-specific data. 
 * </p>
 * 
 * @author Wittybrains
 *
 */
@Component
public class FlashUserDetailsService implements UserDetailsService {

	private UserService userService;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HttpServletRequest request;

	@Autowired
	public FlashUserDetailsService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByUsername(username);
		logger.info("username : " + request.getParameter("username"));
		logger.info("grant_type : " + request.getParameter("grant_type"));
		logger.info(Constants.UUID + request.getHeader(Constants.UUID));
		logger.info("Authorization : " + request.getHeader("Authorization"));
		if (user == null) {
			logger.error("Invalid email ID.");
			logger.error(" Authentication failure for" + request.getRemoteAddr());
			throw new UsernameNotFoundException("Invalid email ID.");
		}
//		switch (user.getRole().getUserType().getName()) {
//		case Customer:
//			Customer customer = user.getCustomerUser().getCustomer();
//			if (!customer.isActive()) {
//				logger.error("User belons to inactive customer.");
//				throw new UnauthorizedException("User belons to inactive customer.");
//			}
//			if (!customer.getOrganisation().isActive()) {
//				logger.error("User belons to inactive Organisation.");
//				throw new UnauthorizedException("User belons to inactive Organisation.");
//			}
//			break;
//
//		case QC_Team:
//			if (!user.getQcUser().getQcTeam().isActive()) {
//				logger.error("User belons to inactive QC team.");
//				throw new UnauthorizedException("User belons to inactive QC team.");
//			}
//			break;
//		case Organisation:
//			Organisation organisation = user.getOrganisationUser().getOrganisation();
//			if (!organisation.isActive()) {
//				logger.error("User belons to inactive Organisation.");
//				throw new UnauthorizedException("User belons to inactive Organisation.");
//			}
//			break;
//		case Admin:
//			break;
//		case Flash_Agent:
//			break;
//		default:
//			logger.error("Invalid role.");
//			throw new BadRequestException("Invalid role.");
//		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		UserDetails userDetails = new FlashUserDetails(user.getUserid(), user.getUsername(), user.getPassword(),authorities);
		return userDetails;
	}
}