package net.datenwerke.rs.passwordpolicy.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datenwerke.rs.core.service.reportserver.ReportServerModule;
import net.datenwerke.rs.utils.crypto.PasswordHasher;
import net.datenwerke.security.service.usermanager.UserPropertiesService;
import net.datenwerke.security.service.usermanager.entities.User;

public class BsiPasswordPolicyUserMetadata {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	private final static String USER_PROPERTY_LAST_SUCCESSFUL_LOGIN_KEY = "bsiPasswordPolicy:lastSuccessfulLogin";
	private final static String USER_PROPERTY_LAST_FAILED_LOGIN_KEY = "bsiPasswordPolicy:lastFailedLogin";
	private final static String USER_PROPERTY_LAST_CHANGED_PASSWORD = "bsiPasswordPolicy:lastChangedPassword";
	private final static String USER_PROPERTY_FAILED_LOGIN_COUNTS = "bsiPasswordPolicy:failedLoginCount";
	private final static String USER_PROPERTY_RECENT_PASSWORDS = "bsiPasswordPolicy:recentPasswords";
	private final static String USER_PROPERTY_MUST_CHANGE_PASSWD = "bsiPasswordPolicy:mustChangePassword";
	
	private Date lastSuccessfulLogin;
	private Date lastFailedLogin;
	private Date lastChangedPassword;
	private Date accountExpirationDate;

	private int failedLoginCount = -1;
	private List<String> recentPasswords;
	
	private Boolean accountInhibited;
	private Boolean enforcePasswordChange;
	private boolean resetLastChangedPassword;
	private boolean resetLastFailedLogin;
	private boolean resetAccountExpirationDate;
	private boolean resetLastSuccessfulLogin;

	private void setUserPropertyIfChanged(UserPropertiesService userPropertiesService, User user, String propName, String newValue){
		String oldVal = userPropertiesService.getPropertyValue(user, propName);
		if(null == newValue || (!newValue.equals(oldVal))){
			userPropertiesService.setPropertyValue(user, propName, newValue);
		}
	}
	
	public void updateUser(User user, UserPropertiesService userPropertiesService){
		if(null != lastSuccessfulLogin)
			setUserPropertyIfChanged(userPropertiesService, user, USER_PROPERTY_LAST_SUCCESSFUL_LOGIN_KEY, Long.toString(lastSuccessfulLogin.getTime()));
		else if(resetLastSuccessfulLogin)
			userPropertiesService.removeProperty(user, USER_PROPERTY_LAST_SUCCESSFUL_LOGIN_KEY);
		
		if(null != lastFailedLogin)
			setUserPropertyIfChanged(userPropertiesService, user, USER_PROPERTY_LAST_FAILED_LOGIN_KEY, Long.toString(lastFailedLogin.getTime()));
		else if(resetLastFailedLogin)
			userPropertiesService.removeProperty(user, USER_PROPERTY_LAST_FAILED_LOGIN_KEY);
		
		if(null != lastChangedPassword)
			setUserPropertyIfChanged(userPropertiesService, user, USER_PROPERTY_LAST_CHANGED_PASSWORD, Long.toString(lastChangedPassword.getTime()));
		else if(resetLastChangedPassword)
			userPropertiesService.removeProperty(user, USER_PROPERTY_LAST_CHANGED_PASSWORD);

		if(failedLoginCount != -1)
			setUserPropertyIfChanged(userPropertiesService, user, USER_PROPERTY_FAILED_LOGIN_COUNTS, Integer.toString(failedLoginCount));
		
		if(null != recentPasswords && !recentPasswords.isEmpty()){
			try {
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(bos);
				oos.writeObject(recentPasswords.toArray(new String[0]));
				oos.close();
				String encoded = new String(Base64.encodeBase64(bos.toByteArray()));
				setUserPropertyIfChanged(userPropertiesService, user, USER_PROPERTY_RECENT_PASSWORDS, encoded);
			} catch (IOException e) {
				logger.info( "Error writing list of recent passwords", e);
			}
		}
		
		if(null != accountInhibited)
			setUserPropertyIfChanged(userPropertiesService, user, ReportServerModule.USER_PROPERTY_ACCOUNT_INHIBITED, Boolean.toString(accountInhibited));
		
		if(null != enforcePasswordChange)
			setUserPropertyIfChanged(userPropertiesService, user, USER_PROPERTY_MUST_CHANGE_PASSWD, Boolean.toString(enforcePasswordChange));
		
		if(null != accountExpirationDate)
			setUserPropertyIfChanged(userPropertiesService, user, ReportServerModule.USER_PROPERTY_ACCOUNT_EXPIRATION_DATE, Long.toString(accountExpirationDate.getTime()));
		else if(resetAccountExpirationDate)
			userPropertiesService.removeProperty(user, ReportServerModule.USER_PROPERTY_ACCOUNT_EXPIRATION_DATE);
		
	}
	
	public void loadfromUser(User user, UserPropertiesService userPropertiesService){
		String lastSuccessfulLogin = userPropertiesService.getPropertyValue(user, USER_PROPERTY_LAST_SUCCESSFUL_LOGIN_KEY);
		String lastFailedLogin = userPropertiesService.getPropertyValue(user, USER_PROPERTY_LAST_FAILED_LOGIN_KEY);
		String lastChangedPassword = userPropertiesService.getPropertyValue(user, USER_PROPERTY_LAST_CHANGED_PASSWORD);
		String failedLoginCount = userPropertiesService.getPropertyValue(user, USER_PROPERTY_FAILED_LOGIN_COUNTS);
		String recentPasswords = userPropertiesService.getPropertyValue(user, USER_PROPERTY_RECENT_PASSWORDS);
		String accountInhibited = userPropertiesService.getPropertyValue(user, ReportServerModule.USER_PROPERTY_ACCOUNT_INHIBITED);
		String enforcePasswordChange = userPropertiesService.getPropertyValue(user, USER_PROPERTY_MUST_CHANGE_PASSWD);
		String accountExporationDate = userPropertiesService.getPropertyValue(user, ReportServerModule.USER_PROPERTY_ACCOUNT_EXPIRATION_DATE);
		
		if(null != lastSuccessfulLogin)
			this.lastSuccessfulLogin = new Date(Long.parseLong(lastSuccessfulLogin));

		if(null != lastFailedLogin)
			this.lastFailedLogin = new Date(Long.parseLong(lastFailedLogin));
			
		if(null != lastChangedPassword)
			this.lastChangedPassword = new Date(Long.parseLong(lastChangedPassword));
		
		if(null != failedLoginCount)
			this.failedLoginCount = Integer.parseInt(failedLoginCount);
		
		if(null != recentPasswords){
			try {
				byte[] oBytes = Base64.decodeBase64(recentPasswords.getBytes());
				ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(oBytes));
				Object ob = ois.readObject();
				if(ob instanceof String[]){
					this.recentPasswords = new ArrayList<String>(Arrays.asList((String[])ob));
				}
			} catch (IOException e) {
				logger.info( "Error reading list of recent passwords", e);
			} catch (ClassNotFoundException e) {
				logger.info( "Error reading list of recent passwords", e);
			}
		}
		
		if(null != accountInhibited)
			this.accountInhibited = Boolean.parseBoolean(accountInhibited);
		
		if(null != enforcePasswordChange)
			this.enforcePasswordChange = Boolean.parseBoolean(enforcePasswordChange);
		
		if(null != accountExporationDate)
			this.accountExpirationDate = new Date(Long.parseLong(accountExporationDate));
			
	}

	public Date getLastSuccessfulLogin() {
		return lastSuccessfulLogin;
	}

	public void setLastSuccessfulLogin(Date lastSuccessfulLogin) {
		this.lastSuccessfulLogin = lastSuccessfulLogin;
		if(null == lastSuccessfulLogin)
			resetLastSuccessfulLogin = true;
	}

	public Date getLastFailedLogin() {
		return lastFailedLogin;
	}

	public void setLastFailedLogin(Date lastFailedLogin) {
		this.lastFailedLogin = lastFailedLogin;
		if(null == lastFailedLogin)
			resetLastFailedLogin = true;
	}

	public Date getLastChangedPassword() {
		return lastChangedPassword;
	}

	public void setLastChangedPassword(Date lastChangedPassword) {
		this.lastChangedPassword = lastChangedPassword;
		if(null == lastChangedPassword)
			resetLastChangedPassword = true;
		else
			this.enforcePasswordChange = false;
		
	}

	public int getFailedLoginCount() {
		return failedLoginCount;
	}

	public void setFailedLoginCount(int failedLoginCount) {
		this.failedLoginCount = failedLoginCount;
	}
	
	public Boolean getAccountInhibited() {
		return accountInhibited;
	}
	
	public boolean isEnforcePasswordChange(){
		return (null != enforcePasswordChange && enforcePasswordChange);
	}
	
	public void setAccountInhibited(Boolean accountInhibited) {
		this.accountInhibited = accountInhibited;
	}
	
	public void registerFailedLogin(){
		this.failedLoginCount++;
		this.lastFailedLogin = new Date();
	}
	
	public void registerSuccessfulLogin(){
		this.failedLoginCount = 0;
		this.lastSuccessfulLogin = new Date();
	}

	public void enforcePasswordChangeOnNextLogin(){
		this.enforcePasswordChange = true;
	}
	
	public List<String> getRecentPasswords() {
		return recentPasswords;
	}
	
	public boolean recentPasswordsContain(String cleartextPassword, int historyLength, PasswordHasher passwordHasher){
		if(null == recentPasswords)
			return false;
		
		List<String> recent = recentPasswords.subList(Math.max(0, recentPasswords.size() - historyLength), recentPasswords.size());//.contains(password);
		for(String recentPassword : recent){
			if(passwordHasher.validatePassword(recentPassword, cleartextPassword))
				return true;
		}
		
		return false;
	}

	public void setRecentPasswords(List<String> recentPasswords) {
		this.recentPasswords = recentPasswords;
	}

	public void addRecentPassword(String password, int historyLength){
		if(null == recentPasswords)
			recentPasswords = new ArrayList<String>();
		
		recentPasswords.add(password);
	
		while(recentPasswords.size() > historyLength)
			recentPasswords.remove(0);
	}
	
	public Date getAccountExpirationDate() {
		return accountExpirationDate;
	}
	
	public void setAccountExpirationDate(Date accountExpirationDate) {
		this.accountExpirationDate = accountExpirationDate;
		if(null == accountExpirationDate)
			resetAccountExpirationDate = true;
		else 
			accountExpirationDate = DateUtils.truncate(accountExpirationDate, Calendar.DAY_OF_MONTH);
	}
}
