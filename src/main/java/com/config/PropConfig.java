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
 * @author Pardeep
 * @version 1.0
 */
// C:/Flash Digital Media/resources/config
// D:/flash
@Component
@PropertySources({
		@PropertySource(value = "file:D:/flash//config//application.properties", ignoreResourceNotFound = false) })
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
	@Value("${database.url}")
	private String databaseUrl;

	/**
	 * The database UserName.
	 */
	@Value("${database.username}")
	private String databaseUsername;

	/**
	 * The database password.
	 */
	@Value("${database.password}")
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
	 * The mobile client id
	 */
	@Value("${flash.mobile.client.id}")
	private String mobileAppClientId;

	/**
	 * The adapter app client id
	 */
	@Value("${flash.adapter.client.id}")
	private String adapterAppClientId;

	/**
	 * The web app secret
	 */
	@Value("${flash.web.api.secret}")
	private String webAppApiSecret;

	/**
	 * The mobile app secret
	 */
	@Value("${flash.mobile.api.secret}")
	private String mobileAppApiSecret;

	/**
	 * The adapter app secret
	 */
	@Value("${flash.adapter.api.secret}")
	private String adapterAppApiSecret;

	/**
	 * The password recovery expiration hours
	 */
	@Value("${password.recovery.token.expiration.hours}")
	private long passwordRecoveryExpirationHours;

	/**
	 * The support email
	 */
	@Value("${support.email}")
	private String supportEmail;

	/**
	 * The app base url
	 */
	@Value("${app.base.url}")
	private String appBaseURL;

	/**
	 * The static resource url
	 */
	@Value("${flash.static.resources.url}")
	private String flashStaticResourseURL;

	// @Value("${flash.schedule.baseFolder.url}")
	// private String gateWayScheduleBaseFolderURL;

	/**
	 * The as run log path
	 */
	@Value("${flash.asrun.log.path}")
	private String asRunLogPath;

	/**
	 * The as run report path
	 */
	@Value("${flash.asrun.report.path}")
	private String asRunReportPath;

	@Value("${spring.mail.port}")
	private String smtpPort;

	@Value("${spring.mail.host}")
	private String smtpHost;

	@Value("${spring.mail.username}")
	private String smtpUserName;

	@Value("${spring.mail.password}")
	private String smtpPassword;

	@Value("${spring.accessToken.seconds}")
	private int accessTokenExpirySeconds;

	public PropConfig() {
		logger.info("PropConfog initialized");
	}

	// /**
	// * @return the gateWayScheduleBaseFolderURL
	// */
	// public String getGateWayScheduleBaseFolderURL() {
	// return gateWayScheduleBaseFolderURL;
	// }
	//
	// /**
	// * @param gateWayScheduleBaseFolderURL
	// * the gateWayScheduleBaseFolderURL to set
	// */
	// public void setGateWayScheduleBaseFolderURL(String
	// gateWayScheduleBaseFolderURL) {
	// this.gateWayScheduleBaseFolderURL = gateWayScheduleBaseFolderURL;
	// }

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
	public void setPasswordRecoveryExpirationHours(long passwordRecoveryExpirationHours) {
		this.passwordRecoveryExpirationHours = passwordRecoveryExpirationHours;
	}

	/**
	 * @return the supportEmail
	 */
	public String getSupportEmail() {
		return supportEmail;
	}

	/**
	 * @param supportEmail
	 *            the supportEmail to set
	 */
	public void setSupportEmail(String supportEmail) {
		this.supportEmail = supportEmail;
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
	 * @return the mobileAppClientId
	 */
	public String getMobileAppClientId() {
		return mobileAppClientId;
	}

	/**
	 * @param mobileAppClientId
	 *            the mobileAppClientId to set
	 */
	public void setMobileAppClientId(String mobileAppClientId) {
		this.mobileAppClientId = mobileAppClientId;
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

	/**
	 * @return the mobileAppApiSecret
	 */
	public String getMobileAppApiSecret() {
		return mobileAppApiSecret;
	}

	/**
	 * @param mobileAppApiSecret
	 *            the mobileAppApiSecret to set
	 */
	public void setMobileAppApiSecret(String mobileAppApiSecret) {
		this.mobileAppApiSecret = mobileAppApiSecret;
	}

	/**
	 * @return the adapterAppClientId
	 */
	public String getAdapterAppClientId() {
		return adapterAppClientId;
	}

	/**
	 * @param adapterAppClientId
	 *            the adapterAppClientId to set
	 */
	public void setAdapterAppClientId(String adapterAppClientId) {
		this.adapterAppClientId = adapterAppClientId;
	}

	/**
	 * @return the adapterAppApiSecret
	 */
	public String getAdapterAppApiSecret() {
		return adapterAppApiSecret;
	}

	/**
	 * @param adapterAppApiSecret
	 *            the adapterAppApiSecret to set
	 */
	public void setAdapterAppApiSecret(String adapterAppApiSecret) {
		this.adapterAppApiSecret = adapterAppApiSecret;
	}

	/**
	 * @return the flashStaticResourseURL
	 */
	public String getFlashStaticResourseURL() {
		return flashStaticResourseURL;
	}

	/**
	 * @param flashStaticResourseURL
	 *            the flashStaticResourseURL to set
	 */
	public void setFlashStaticResourseURL(String flashStaticResourseURL) {
		this.flashStaticResourseURL = flashStaticResourseURL;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
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

	/**
	 * @return the asRunLogPath
	 */
	public String getAsRunLogPath() {
		return asRunLogPath;
	}

	/**
	 * @param asRunLogPath
	 *            the asRunLogPath to set
	 */
	public void setAsRunLogPath(String asRunLogPath) {
		this.asRunLogPath = asRunLogPath;
	}

	/**
	 * @return the asRunReportPath
	 */
	public String getAsRunReportPath() {
		return asRunReportPath;
	}

	/**
	 * @param asRunReportPath
	 *            the asRunReportPath to set
	 */
	public void setAsRunReportPath(String asRunReportPath) {
		this.asRunReportPath = asRunReportPath;
	}

	/**
	 * @return the smtpPort
	 */
	public String getSmtpPort() {
		return smtpPort;
	}

	/**
	 * @param smtpPort
	 *            the smtpPort to set
	 */
	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}

	/**
	 * @return the smtpHost
	 */
	public String getSmtpHost() {
		return smtpHost;
	}

	/**
	 * @param smtpHost
	 *            the smtpHost to set
	 */
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	/**
	 * @return the smtpUserName
	 */
	public String getSmtpUserName() {
		return smtpUserName;
	}

	/**
	 * @param smtpUserName
	 *            the smtpUserName to set
	 */
	public void setSmtpUserName(String smtpUserName) {
		this.smtpUserName = smtpUserName;
	}

	/**
	 * @return the smtpPassword
	 */
	public String getSmtpPassword() {
		return smtpPassword;
	}

	/**
	 * @param smtpPassword
	 *            the smtpPassword to set
	 */
	public void setSmtpPassword(String smtpPassword) {
		this.smtpPassword = smtpPassword;
	}

	/**
	 * @return the accessTokenExpirySeconds
	 */
	public int getAccessTokenExpirySeconds() {
		return accessTokenExpirySeconds;
	}

	/**
	 * @param accessTokenExpirySeconds
	 *            the accessTokenExpirySeconds to set
	 */
	public void setAccessTokenExpirySeconds(int accessTokenExpirySeconds) {
		this.accessTokenExpirySeconds = accessTokenExpirySeconds;
	}

}
