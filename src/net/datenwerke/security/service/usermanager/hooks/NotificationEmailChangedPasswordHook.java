package net.datenwerke.security.service.usermanager.hooks;

import java.util.HashMap;

import org.apache.commons.configuration2.Configuration;

import com.google.inject.Inject;

import net.datenwerke.gf.service.localization.RemoteMessageService;
import net.datenwerke.rs.base.service.parameterreplacements.provider.UserForJuel;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.core.service.mail.MailService;
import net.datenwerke.rs.core.service.mail.MailTemplate;
import net.datenwerke.rs.core.service.mail.SimpleMail;
import net.datenwerke.rs.passwordpolicy.service.locale.PasswordPolicyMessages;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.security.service.usermanager.entities.User;

public class NotificationEmailChangedPasswordHook implements PasswordManualSetHook {

   private final MailService mailService;
   private final ConfigService configService;
   private final RemoteMessageService remoteMessageService;

   private final static String PROPERTY_USER = "user";

   public static final String CONFIG_FILE = "security/notifications.cf";

   @Inject
   public NotificationEmailChangedPasswordHook(RemoteMessageService remoteMessageService, MailService mailService,
         ConfigService configService) {
      this.remoteMessageService = remoteMessageService;
      this.mailService = mailService;
      this.configService = configService;
   }

   @Override
   public void passwordWasManuallySet(User user, boolean createdPassword) {

      if (null == user.getEmail())
         return;

      Configuration config = configService.getConfigFailsafe(CONFIG_FILE);
      String function = null;
      String content = null;
      String subject = null;
      /* For Email notification */
      if (createdPassword) {
         function = "createdpassword";
         subject = PasswordPolicyMessages.INSTANCE.createdPasswordSubject();
         content = PasswordPolicyMessages.INSTANCE.createdPasswordIntro() + "\n\n" + user.getUsername() + "\n\n"
               + PasswordPolicyMessages.INSTANCE.createdPasswordEnd();
      } else {
         function = "changedpassword";
         subject = PasswordPolicyMessages.INSTANCE.changedPasswordSubject();
         content = PasswordPolicyMessages.INSTANCE.changedPasswordIntro() + "\n\n" + user.getUsername() + "\n\n"
               + PasswordPolicyMessages.INSTANCE.changedPasswordEnd();
      }

      boolean disabled = config.getBoolean(function + "[@disabled]", false);
      if (disabled)
         return;

      /* Get the notification file */
      String mailTemplate = config.getString(function + ".email.text", content);
      String mailSubject = config.getString(function + ".email.subject", subject);

      /* prepare value map for template */
      HashMap<String, Object> replacements = new HashMap<String, Object>();
      replacements.put(PROPERTY_USER, UserForJuel.createInstance(user));

      String currentLanguage = LocalizationServiceImpl.getLocale().getLanguage();
      replacements.put("msgs", remoteMessageService.getMessages(currentLanguage));

      /* fill email template */
      MailTemplate template = new MailTemplate();
      template.setMessageTemplate(mailTemplate);
      template.setSubjectTemplate(mailSubject);
      template.setDataMap(replacements);

      SimpleMail mail = mailService.newTemplateMail(template);
      mail.setToRecipients(user.getEmail());

      /* Sending the email */
      mailService.sendMailSync(mail);
   }

}
