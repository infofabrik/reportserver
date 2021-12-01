package net.datenwerke.rs.passwordpolicy.service;

import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.passwordpolicy.service.hooker.BsiPasswordPolicyChangePasswordHook;
import net.datenwerke.rs.passwordpolicy.service.hooker.BsiPasswordPolicyPasswordSetHook;
import net.datenwerke.rs.passwordpolicy.service.hooker.BsiPasswordPolicyPostAuthenticateHook;
import net.datenwerke.security.service.authenticator.hooks.PostAuthenticateHook;
import net.datenwerke.security.service.usermanager.hooks.ChangePasswordHook;
import net.datenwerke.security.service.usermanager.hooks.PasswordSetHook;

import org.apache.commons.configuration2.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * A password policy based on the recommendations from
 * https://www.bsi.bund.de/ContentBSI/grundschutz/kataloge/m/m04/m04048.html
 * 
 *
 */
public class BsiPasswordPolicy implements PasswordPolicy {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	public final static String BSI_PASSWORD_POLICY_CHARSET_KEY = "rs.security.passwordpolicy.bsipasswordpolicy.characterset";
	public final static String BSI_PASSWORD_POLICY_OCCURRENCES_KEY = "rs.security.passwordpolicy.bsipasswordpolicy.choosemin";

	public final static String BSI_PASSWORD_POLICY_PSWD_MAX_AGE = "rs.security.passwordpolicy.bsipasswordpolicy.pswd.maxage";
	public final static String BSI_PASSWORD_POLICY_PSWD_MIN_AGE = "rs.security.passwordpolicy.bsipasswordpolicy.pswd.minage";
	public final static String BSI_PASSWORD_POLICY_PSWD_MIN_LENGTH = "rs.security.passwordpolicy.bsipasswordpolicy.pswd.minlength";
	public final static String BSI_PASSWORD_POLICY_HISTORY_SIZE = "rs.security.passwordpolicy.bsipasswordpolicy.historysize";
	public final static String BSI_PASSWORD_POLICY_ACCOUNT_LOCKOUT_THRESHOLD  = "rs.security.passwordpolicy.bsipasswordpolicy.lockoutthreshold";
	public final static String BSI_PASSWORD_POLICY_ACCOUNT_LOCKOUT_RESET_TIMEOUT = "rs.security.passwordpolicy.bsipasswordpolicy.lockoutresettimeout";
	
	private CharacterClassBasedPasswordComplexitySpecification passwordComplexitySpecification;
	
	private int passwordMaxAge;
	private int passwordMinAge;
	private int historySize;
	private int accountLockoutThreshold;
	private int accountLockoutAutoResetTimeout;
	
	private final ConfigService configService;
	
	private final Provider<BsiPasswordPolicyChangePasswordHook> passwordChangeHookProvider;
	private final Provider<BsiPasswordPolicyPasswordSetHook> passwordSetHookProvider;
	private final Provider<BsiPasswordPolicyPostAuthenticateHook> postAuthenticateHookProvider;
	
	private boolean valid;
	
	@Inject
	public BsiPasswordPolicy(
		ConfigService configService, 
		Provider<BsiPasswordPolicyChangePasswordHook> passwordChangeHookProvider, 
		Provider<BsiPasswordPolicyPasswordSetHook> passwordSetHookProvider, 
		Provider<BsiPasswordPolicyPostAuthenticateHook> postAuthenticateHookProvider
		) {

		this.configService = configService;
		this.passwordChangeHookProvider = passwordChangeHookProvider;
		this.passwordSetHookProvider = passwordSetHookProvider;
		this.postAuthenticateHookProvider = postAuthenticateHookProvider;
	}
	
	public void loadConfig(){
			Configuration rsProperties = configService.getConfig(PasswordPolicyModule.CONFIG_FILE);

			/* retrieve configuration from .properties */
			passwordMaxAge = rsProperties.getInt(BSI_PASSWORD_POLICY_PSWD_MAX_AGE);
			passwordMinAge = rsProperties.getInt(BSI_PASSWORD_POLICY_PSWD_MIN_AGE);
			historySize = rsProperties.getInt(BSI_PASSWORD_POLICY_HISTORY_SIZE);
			accountLockoutThreshold = rsProperties.getInt(BSI_PASSWORD_POLICY_ACCOUNT_LOCKOUT_THRESHOLD);
			accountLockoutAutoResetTimeout = rsProperties.getInt(BSI_PASSWORD_POLICY_ACCOUNT_LOCKOUT_RESET_TIMEOUT);

			/* Build password complexity specification from config */
			String[] charsets = rsProperties.getStringArray(BSI_PASSWORD_POLICY_CHARSET_KEY);
			String[] occurrences = rsProperties.getStringArray(BSI_PASSWORD_POLICY_OCCURRENCES_KEY);
			int passwordMinLength = rsProperties.getInt(BSI_PASSWORD_POLICY_PSWD_MIN_LENGTH);

			passwordComplexitySpecification = new CharacterClassBasedPasswordComplexitySpecification(passwordMinLength);

			for(int i = 0; i < charsets.length; i++){
				int minOccurrences = 0;

				if(i < occurrences.length)
					minOccurrences = Integer.valueOf(occurrences[i]);


				passwordComplexitySpecification.addSelectionSpecification(new CharacterSelectionSpecification(new CharacterClass(charsets[i]), minOccurrences));
			}

			valid = true;
	}

	public boolean isValid(){
		return valid;
	}
	

	@Override
	public CharacterClassBasedPasswordComplexitySpecification getPasswordComplexitySpecification() {
		return passwordComplexitySpecification;
	}
	
	@Override
	public PostAuthenticateHook getPostAuthenticateHooker() {
		return postAuthenticateHookProvider.get();
	}
	
	@Override
	public ChangePasswordHook getChangePasswordHooker(){
		return passwordChangeHookProvider.get();
	}


	public int getPasswordMaxAge() {
		return passwordMaxAge;
	}


	public int getPasswordMinAge() {
		return passwordMinAge;
	}


	public int getHistorySize() {
		return historySize;
	}


	public int getAccountLockoutThreshold() {
		return accountLockoutThreshold;
	}


	public int getAccountLockoutAutoResetTimeout() {
		return accountLockoutAutoResetTimeout;
	}

	@Override
	public PasswordSetHook getPasswordSetHooker() {
		return passwordSetHookProvider.get();
	}
	
}
