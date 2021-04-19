package net.datenwerke.rs.passwordpolicy.service.lostpassword;

import java.util.HashMap;

import javax.persistence.NoResultException;

import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.localization.RemoteMessageService;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.rs.base.service.parameterreplacements.provider.UserForJuel;
import net.datenwerke.rs.core.service.mail.MailService;
import net.datenwerke.rs.core.service.mail.MailTemplate;
import net.datenwerke.rs.core.service.mail.SimpleMail;
import net.datenwerke.rs.passwordpolicy.service.PasswordGenerator;
import net.datenwerke.rs.passwordpolicy.service.events.UserLostPasswordEvent;
import net.datenwerke.rs.passwordpolicy.service.locale.PasswordPolicyMessages;
import net.datenwerke.rs.utils.config.ConfigService;
import net.datenwerke.rs.utils.crypto.PasswordHasher;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.security.ext.client.crypto.rpc.CryptoRpcService;
import net.datenwerke.security.service.security.locale.SecurityMessages;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.UserPropertiesService;
import net.datenwerke.security.service.usermanager.entities.User;

public class LostPasswordServiceImpl implements LostPasswordService{
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	private final static String PROPERTY_USER = "user";
	private final static String PROPERTY_TMP_PASSWORD = "password";
	
	private final SecurityMessages messages = LocalizationServiceImpl.getMessages(SecurityMessages.class);

	private final UserManagerService userManagerService;
	private final PasswordHasher passwordHasher;
	private final Provider<PasswordGenerator> passwordGenerator;
	private final MailService mailService;
	private final EventBus eventBus;
	private final UserPropertiesService userPropertiesService;
	private final CryptoRpcService cryptoRpcService;
	private final ConfigService configService;
	private final RemoteMessageService remoteMessageService;
	
	@Inject
	public LostPasswordServiceImpl(
			EventBus eventBus,
			PasswordHasher passwordHasher, 
			UserManagerService userManagerService, 
			Provider<PasswordGenerator> passwordGenerator, 
			UserPropertiesService userPropertiesService, 
			ConfigService configService,
			RemoteMessageService remoteMessageService,
			MailService mailService,
			CryptoRpcService cryptoRpcService
	) {
		this.eventBus = eventBus;
		this.passwordHasher = passwordHasher;
		this.userManagerService = userManagerService;
		this.passwordGenerator = passwordGenerator;
		this.userPropertiesService = userPropertiesService;
		this.configService = configService;
		this.remoteMessageService = remoteMessageService;
		this.mailService = mailService;
		this.cryptoRpcService = cryptoRpcService;
	}
	
	@Override
	public String requestNewPassword(String username) throws ExpectedException{
		Configuration config = configService.getConfigFailsafe(LostPasswordModule.CONFIG_FILE);
		
		String mailTemplate = config.getString("lostpassword.email.text", messages.lostPasswordMessageTemplate());
		String mailSubject = config.getString("lostpassword.email.subject", messages.lostPasswordMessageSubject());
		boolean indicateWrongUser = config.getBoolean("lostpassword[@indicateWrongUsername]", false);
		
		try{
			User user = userManagerService.getUserByName(username);
			
			if(null == user)
				if(indicateWrongUser)
					return PasswordPolicyMessages.INSTANCE.exceptionUserNotExists(username);
				else return PasswordPolicyMessages.INSTANCE.lostPasswordMessageConfirmation();
			
			PasswordGenerator pwdGen = passwordGenerator.get();
			
			if(null == pwdGen)
				throw new RuntimeException("No known password generator");
				
			/* generate temporary password */
			String randomPassword = pwdGen.newPassword();
			
			/* use the user's salt */
			String salt = cryptoRpcService.getUserSalt(user.getUsername());

			/* store the password in the users properties */
			userPropertiesService.setPropertyValue(user, LostPasswordModule.USER_PROPERTY_TMP_PASSWORD, passwordHasher.hashPassword(randomPassword, salt));
			userPropertiesService.setPropertyValue(user, LostPasswordModule.USER_PROPERTY_TMP_PASSWORD_DATE, Long.toString(System.currentTimeMillis()));
			
			userManagerService.merge(user);

			/* prepare value map for template */
			HashMap<String, Object> replacements = new HashMap<String, Object>();
			replacements.put(PROPERTY_TMP_PASSWORD, randomPassword);
			replacements.put(PROPERTY_USER, UserForJuel.createInstance(user));
			
			String currentLanguage = LocalizationServiceImpl.getLocale().getLanguage();
			replacements.put("msgs", remoteMessageService.getMessages(currentLanguage));
			
			/* fill email template*/
			MailTemplate template = new MailTemplate();
			template.setMessageTemplate(mailTemplate);
			template.setSubjectTemplate(mailSubject);
			template.setDataMap(replacements);
			
			/* create and send message */
			try{
				SimpleMail mailMessage = mailService.newTemplateMail(template);
				mailMessage.setToRecipients(user.getEmail());
				mailService.sendMailSync(mailMessage);
			} catch(Exception e){
				logger.warn("Could not send password.", e);
				return PasswordPolicyMessages.INSTANCE.exceptionCouldNotSendPassword(e.getMessage());
			}
			
			/* fire event */
			eventBus.fireEvent(new UserLostPasswordEvent(user));
			
		}catch(NoResultException e){
			if(indicateWrongUser)
				return PasswordPolicyMessages.INSTANCE.exceptionUserNotExists(username);
		}
		
		return PasswordPolicyMessages.INSTANCE.lostPasswordMessageConfirmation();
	}

}
