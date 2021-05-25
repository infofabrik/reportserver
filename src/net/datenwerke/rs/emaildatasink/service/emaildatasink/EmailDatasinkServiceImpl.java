package net.datenwerke.rs.emaildatasink.service.emaildatasink;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Provider;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule;
import net.datenwerke.rs.core.service.mail.MailBuilderFactory;
import net.datenwerke.rs.core.service.mail.MailService;
import net.datenwerke.rs.core.service.mail.SimpleAttachment;
import net.datenwerke.rs.core.service.mail.SimpleMail;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.annotations.DefaultEmailDatasink;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.EmailDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.utils.config.ConfigService;
import net.datenwerke.rs.utils.misc.MimeUtils;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.usermanager.entities.User;

public class EmailDatasinkServiceImpl implements EmailDatasinkService {

   private final Provider<AuthenticatorService> authenticatorServiceProvider;
   private final Provider<MimeUtils> mimeUtilsProvider;

   public static final int DEFAULT_BUFFER_SIZE = 8192;

   private static final String PROPERTY_EMAIL_DISABLED = "email[@disabled]";
   private static final String PROPERTY_EMAIL_SCHEDULER_ENABLED = "email[@supportsScheduling]";

   private final Provider<ConfigService> configServiceProvider;
   private final Provider<MailBuilderFactory> mailBuilderFactoryProvider;
   private final Provider<MailService> mailServiceProvider;
   private final Provider<Optional<EmailDatasink>> defaultEmailDatasinkProvider;

   private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

   @Inject
   public EmailDatasinkServiceImpl(
         Provider<ConfigService> configServiceProvider,
         Provider<MailBuilderFactory> mailBuilderFactoryProvider, 
         Provider<MailService> mailServiceProvider,
         Provider<AuthenticatorService> authenticatorServiceProvider,
         Provider<MimeUtils> mimeUtilsProvider,
         @DefaultEmailDatasink Provider<Optional<EmailDatasink>> defaultEmailDatasinkProvider
         ) {
      this.configServiceProvider = configServiceProvider;
      this.mailBuilderFactoryProvider = mailBuilderFactoryProvider;
      this.mailServiceProvider = mailServiceProvider;
      this.authenticatorServiceProvider = authenticatorServiceProvider;
      this.mimeUtilsProvider = mimeUtilsProvider;
      this.defaultEmailDatasinkProvider = defaultEmailDatasinkProvider;
   }

   @Override
   public void sendToEmailDatasink(Object report, EmailDatasink emailDatasink, String subject, String body,
         List<User> recipients, String filename, boolean sendSyncEmail) throws IOException {
      Objects.requireNonNull(emailDatasink, "datasink is null!");
      Objects.requireNonNull(filename);

      SimpleMail mail = mailBuilderFactoryProvider.get().create(subject, body, recipients)
            .withEmailDatasink(emailDatasink)
            .withAttachments(Arrays.asList(new SimpleAttachment(report, 
                  mimeUtilsProvider.get().getMimeTypeByExtension(filename), filename)))
            .build();

      if (sendSyncEmail)
         mailServiceProvider.get().sendMailSync(Optional.of(emailDatasink), mail);
      else
         mailServiceProvider.get().sendMail(Optional.of(emailDatasink), mail);
   }

   @Override
   public Map<StorageType, Boolean> getEmailEnabledConfigs() {
      Map<StorageType, Boolean> configs = new HashMap<>();
      configs.put(StorageType.EMAIL, isEmailEnabled());
      configs.put(StorageType.EMAIL_SCHEDULING, isEmailSchedulingEnabled());
      return configs;
   }

   @Override
   public boolean isEmailEnabled() {
      return !configServiceProvider.get().getConfigFailsafe(DatasinkModule.CONFIG_FILE).getBoolean(PROPERTY_EMAIL_DISABLED, false);
   }

   @Override
   public boolean isEmailSchedulingEnabled() {
      return configServiceProvider.get().getConfigFailsafe(DatasinkModule.CONFIG_FILE).getBoolean(PROPERTY_EMAIL_SCHEDULER_ENABLED,
            true);
   }

   @Override
   public void testEmailDatasink(EmailDatasink emailDatasink) throws IOException {
      if (!isEmailEnabled())
         throw new IllegalStateException("Email datasink is disabled");

      String emailText = "ReportServer Email Datasink Test";
      sendToEmailDatasink(emailText + " " + dateFormat.format(Calendar.getInstance().getTime()), emailDatasink,
            emailText, emailText + " " + dateFormat.format(Calendar.getInstance().getTime()),
            Arrays.asList(authenticatorServiceProvider.get().getCurrentUser()), "reportserver-email-datasink-test.txt",
            true);
   }

   @Override
   public Optional<EmailDatasink> getDefaultEmailDatasink() {
      return defaultEmailDatasinkProvider.get();
   }

}
