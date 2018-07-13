package com.config;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Properties Configuration bean maps all the properties presents in
 * application.properties.
 * </p>
 * 
 * @version 1.0
 */
// C:/Flash Digital Media/resources/config
// D:/flash
@Component
// @PropertySources({
// @PropertySource(value = "file:/home/wittybrains/application.properties",
// ignoreResourceNotFound = false) })
public class PropConfig implements InitializingBean {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * The database Driver.
	 */
	@Value("${database.driver}")
	private String databaseDriver;

	/**
	 * The database URL.
	 */
	@Value("${spring.datasource.url}")
	private String databaseUrl;

	/**
	 * The database UserName.
	 */
	@Value("${spring.datasource.username}")
	private String databaseUsername;

	/**
	 * The database password.
	 */
	@Value("${spring.datasource.password}")
	private String databasePassword;

	/**
	 * The boolean property to whether show SQL or not.
	 */
	@Value("${database.showSql}")
	private boolean showSql;

	/**
	 * The boolean property to whether generate DDL or not.
	 */
	@Value("${database.generateDdl}")
	private boolean generateDdl;

	/**
	 * The database vendor.
	 */
	@Value("${database.vendor}")
	private Database vendor;

	/**
	 * The server port address.
	 */
	@Value("${server.port}")
	private Long serverPort;

	/**
	 * The web client id
	 */
	@Value("${flash.web.client.id}")
	private String webAppClientId;

	/**
	 * The web app secret
	 */
	@Value("${flash.web.api.secret}")
	private String webAppApiSecret;
	/**
	 * The password recovery expiration hours
	 */
	@Value("${password.recovery.token.expiration.hours}")
	private long passwordRecoveryExpirationHours;


	/**
	 * The app base url
	 */
	@Value("${app.base.url}")
	private String appBaseURL;

	@Value("${spring.accessToken.seconds}")
	private int accessTokenExpirySeconds;


	@Value("${spring.mail.port}")
	private String smtpPort;

	@Value("${spring.mail.host}")
	private String smtpHost;

	@Value("${spring.mail.username}")
	private String smtpUserName;

	@Value("${spring.mail.password}")
	private String smtpPassword;
	
	public PropConfig() {
		logger.info("PropConfog initialized");
	}

	/**
	 * @return the databaseDriver
	 */
	public String getDatabaseDriver() {
		return databaseDriver;
	}

	/**
	 * @param databaseDriver
	 *            the databaseDriver to set
	 */
	public void setDatabaseDriver(String databaseDriver) {
		this.databaseDriver = databaseDriver;
	}

	/**
	 * @return the databaseUrl
	 */
	public String getDatabaseUrl() {
		return databaseUrl;
	}

	/**
	 * @param databaseUrl
	 *            the databaseUrl to set
	 */
	public void setDatabaseUrl(String databaseUrl) {
		this.databaseUrl = databaseUrl;
	}

	/**
	 * @return the databaseUsername
	 */
	public String getDatabaseUsername() {
		return databaseUsername;
	}

	/**
	 * @param databaseUsername
	 *            the databaseUsername to set
	 */
	public void setDatabaseUsername(String databaseUsername) {
		this.databaseUsername = databaseUsername;
	}

	/**
	 * @return the databasePassword
	 */
	public String getDatabasePassword() {
		return databasePassword;
	}

	/**
	 * @param databasePassword
	 *            the databasePassword to set
	 */
	public void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
	}

	/**
	 * @return the showSql
	 */
	public boolean isShowSql() {
		return showSql;
	}

	/**
	 * @param showSql
	 *            the showSql to set
	 */
	public void setShowSql(boolean showSql) {
		this.showSql = showSql;
	}

	/**
	 * @return the generateDdl
	 */
	public boolean isGenerateDdl() {
		return generateDdl;
	}

	/**
	 * @param generateDdl
	 *            the generateDdl to set
	 */
	public void setGenerateDdl(boolean generateDdl) {
		this.generateDdl = generateDdl;
	}

	/**
	 * @return the vendor
	 */
	public Database getVendor() {
		return vendor;
	}

	/**
	 * @param vendor
	 *            the vendor to set
	 */
	public void setVendor(Database vendor) {
		this.vendor = vendor;
	}

	/**
	 * @return the serverPort
	 */
	public Long getServerPort() {
		return serverPort;
	}

	/**
	 * @param serverPort
	 *            the serverPort to set
	 */
	public void setServerPort(Long serverPort) {
		this.serverPort = serverPort;
	}

	/**
	 * @return the webAppClientId
	 */
	public String getWebAppClientId() {
		return webAppClientId;
	}

	/**
	 * @param webAppClientId
	 *            the webAppClientId to set
	 */
	public void setWebAppClientId(String webAppClientId) {
		this.webAppClientId = webAppClientId;
	}

	/**
	 * @return the passwordRecoveryExpirationTime
	 */
	public long getPasswordRecoveryExpirationHours() {
		return passwordRecoveryExpirationHours;
	}

	/**
	 * @param passwordRecoveryExpirationTime
	 *            the passwordRecoveryExpirationTime to set
	 */
	public void setPasswordRecoveryExpirationHours(
			long passwordRecoveryExpirationHours) {
		this.passwordRecoveryExpirationHours = passwordRecoveryExpirationHours;
	}


	/**
	 * @return the appBaseURL
	 */
	public String getAppBaseURL() {
		return appBaseURL;
	}

	/**
	 * @param appBaseURL
	 *            the appBaseURL to set
	 */
	public void setAppBaseURL(String appBaseURL) {
		this.appBaseURL = appBaseURL;
	}

	/**
	 * @return the webAppApiSecret
	 */
	public String getWebAppApiSecret() {
		return webAppApiSecret;
	}

	/**
	 * @param webAppApiSecret
	 *            the webAppApiSecret to set
	 */
	public void setWebAppApiSecret(String webAppApiSecret) {
		this.webAppApiSecret = webAppApiSecret;
	}
	
	

	public int getAccessTokenExpirySeconds() {
		return accessTokenExpirySeconds;
	}

	public void setAccessTokenExpirySeconds(int accessTokenExpirySeconds) {
		this.accessTokenExpirySeconds = accessTokenExpirySeconds;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

	/**
	 * Allows a set of command to be run after all properties have been set.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		// logger.debug("Application Properties={}", this);
	}

}
