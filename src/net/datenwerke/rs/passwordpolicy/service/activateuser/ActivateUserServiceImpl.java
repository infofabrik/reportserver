package net.datenwerke.rs.passwordpolicy.service.activateuser;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.configuration2.Configuration;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.DwGwtFrameworkBase;
import net.datenwerke.gf.service.localization.RemoteMessageService;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.rs.base.service.parameterreplacements.provider.UserForJuel;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.core.service.mail.MailService;
import net.datenwerke.rs.core.service.mail.MailTemplate;
import net.datenwerke.rs.core.service.mail.SimpleMail;
import net.datenwerke.rs.passwordpolicy.service.BsiPasswordPolicyService;
import net.datenwerke.rs.passwordpolicy.service.BsiPasswordPolicyUserMetadata;
import net.datenwerke.rs.passwordpolicy.service.PasswordGenerator;
import net.datenwerke.rs.passwordpolicy.service.events.ActivatedUserEvent;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.User;

public class ActivateUserServiceImpl implements ActivateUserService {

   private final static String PROPERTY_USER = "user";
   private final static String PROPERTY_URL = "url";
   private final static String PROPERTY_TMP_PASSWORD = "password";

   private final UserManagerService userManagerService;
   private final EventBus eventBus;
   private final Provider<PasswordGenerator> passwordGenerator;
   private final MailService mailService;
   private final Provider<HttpServletRequest> requestProvider;
   private final ConfigService configService;
   private final BsiPasswordPolicyService bsiPasswordPolicyService;
   private final RemoteMessageService remoteMessageService;

   @Inject
   public ActivateUserServiceImpl(
         UserManagerService userManagerService, 
         EventBus eventBus,
         Provider<PasswordGenerator> passwordGenerator, 
         MailService mailService,
         Provider<HttpServletRequest> requestProvider, 
         ConfigService configService,
         BsiPasswordPolicyService bsiPasswordPolicyService, 
         RemoteMessageService remoteMessageService
         ) {
      this.userManagerService = userManagerService;
      this.eventBus = eventBus;
      this.passwordGenerator = passwordGenerator;
      this.mailService = mailService;
      this.requestProvider = requestProvider;
      this.configService = configService;
      this.bsiPasswordPolicyService = bsiPasswordPolicyService;
      this.remoteMessageService = remoteMessageService;
   }

   private String getModuleBaseURL() {
      String url = requestProvider.get().getRequestURL().toString();
      int idx = url.lastIndexOf(DwGwtFrameworkBase.BASE_URL);
      url = url.substring(0, idx) + "/";
      return url;
   }

   @Override
   public void activateAccount(User user, boolean force) throws ExpectedException {
      if ((null != user.getPassword() && !user.getPassword().isEmpty()) && (!force))
         throw new ExpectedException("This users account is already active (non-empty password)");

      if (null == user.getEmail() || "".equals(user.getEmail()))
         throw new ExpectedException("The user does not yet have an email address.");

      PasswordGenerator pwdGen = passwordGenerator.get();

      if (null == pwdGen)
         throw new ExpectedException("No known password generator");

      /* generate temporary password */
      String randomPassword = pwdGen.newPassword();
      userManagerService.setPassword(user, randomPassword);

      /* mark user as requiring password change on next login */
      BsiPasswordPolicyUserMetadata userMetadata = bsiPasswordPolicyService.getUserMetadata(user);

      userMetadata.setFailedLoginCount(0);
      userMetadata.setLastChangedPassword(null);
      userMetadata.enforcePasswordChangeOnNextLogin();

      bsiPasswordPolicyService.updateUserMetadata(user, userMetadata);

      userManagerService.merge(user);

      /* prepare value map for template */
      HashMap<String, Object> replacements = new HashMap<String, Object>();
      replacements.put(PROPERTY_TMP_PASSWORD, randomPassword);
      replacements.put(PROPERTY_URL, getModuleBaseURL());
      replacements.put(PROPERTY_USER, UserForJuel.createInstance(user));

      String currentLanguage = LocalizationServiceImpl.getLocale().getLanguage();
      replacements.put("msgs", remoteMessageService.getMessages(currentLanguage));

      /* fill email temlate */
      MailTemplate template = new MailTemplate();

      Configuration config = configService.getConfig(ActivateUserModule.CONFIG_FILE);
      template.setMessageTemplate(config.getString(ActivateUserModule.PROPERTY_EMAIL_TEXT));
      template.setSubjectTemplate(config.getString(ActivateUserModule.PROPERTY_EMAIL_SUBJECT));
      template.setDataMap(replacements);

      /* create and send message */
      SimpleMail mailMessage = mailService.newTemplateMail(template);
      mailMessage.setToRecipients(user.getEmail());
      mailService.sendMail(mailMessage);

      /* activated the user -> fire event */
      eventBus.fireEvent(new ActivatedUserEvent());
   }

   @Override
   public void activateAccount(User user) throws ExpectedException {
      activateAccount(user, false);
   }

}
