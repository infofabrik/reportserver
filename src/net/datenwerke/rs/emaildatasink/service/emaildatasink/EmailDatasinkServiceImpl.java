package net.datenwerke.rs.emaildatasink.service.emaildatasink;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Provider;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.core.service.mail.MailBuilderFactory;
import net.datenwerke.rs.core.service.mail.MailService;
import net.datenwerke.rs.core.service.mail.SimpleAttachment;
import net.datenwerke.rs.core.service.mail.SimpleMail;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.annotations.DefaultEmailDatasink;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.configs.DatasinkEmailConfig;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.EmailDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.utils.misc.MimeUtils;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.usermanager.entities.User;

public class EmailDatasinkServiceImpl implements EmailDatasinkService {

   private final Provider<AuthenticatorService> authenticatorServiceProvider;
   private final Provider<MimeUtils> mimeUtilsProvider;

   public static final int DEFAULT_BUFFER_SIZE = 8192;

   private final Provider<MailBuilderFactory> mailBuilderFactoryProvider;
   private final Provider<MailService> mailServiceProvider;
   private final Provider<Optional<EmailDatasink>> defaultDatasinkProvider;
   private final Provider<DatasinkService> datasinkServiceProvider;

   private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

   @Inject
   public EmailDatasinkServiceImpl(
         Provider<MailBuilderFactory> mailBuilderFactoryProvider, 
         Provider<MailService> mailServiceProvider,
         Provider<AuthenticatorService> authenticatorServiceProvider,
         Provider<MimeUtils> mimeUtilsProvider,
         @DefaultEmailDatasink Provider<Optional<EmailDatasink>> defaultDatasinkProvider,
         Provider<DatasinkService> datasinkServiceProvider
         ) {
      this.mailBuilderFactoryProvider = mailBuilderFactoryProvider;
      this.mailServiceProvider = mailServiceProvider;
      this.authenticatorServiceProvider = authenticatorServiceProvider;
      this.mimeUtilsProvider = mimeUtilsProvider;
      this.defaultDatasinkProvider = defaultDatasinkProvider;
      this.datasinkServiceProvider = datasinkServiceProvider;
   }

   @Override
   public void exportIntoDatasink(Object report, EmailDatasink emailDatasink, DatasinkConfiguration config) 
         throws DatasinkExportException {
      Objects.requireNonNull(emailDatasink, "datasink is null!");
      if (!(config instanceof DatasinkEmailConfig))
         throw new IllegalStateException("Not a DatasinkEmailConfig config");
      
      DatasinkEmailConfig emailConfig = (DatasinkEmailConfig) config;

      try {
         SimpleMail mail = mailBuilderFactoryProvider.get().create(emailConfig.getSubject(), emailConfig.getBody(), 
               emailConfig.getRecipients())
               .withEmailDatasink(emailDatasink)
               .withAttachments(Arrays.asList(new SimpleAttachment(report, 
                     mimeUtilsProvider.get().getMimeTypeByExtension(emailConfig.getFilename()), emailConfig.getFilename())))
               .build();
   
         if (emailConfig.isSendSyncEmail())
            mailServiceProvider.get().sendMailSync(Optional.of(emailDatasink), mail);
         else
            mailServiceProvider.get().sendMail(Optional.of(emailDatasink), mail);
      } catch (Exception e) {
         throw new DatasinkExportException("An error occurred during datasink export", e);
      }
   }

   @Override
   public void testDatasink(EmailDatasink emailDatasink) throws DatasinkExportException {
      if (!datasinkServiceProvider.get().isEnabled(this))
         throw new IllegalStateException("Email datasink is disabled");

      String emailText = "ReportServer Email Datasink Test";
      exportIntoDatasink(emailText + " " + dateFormat.format(Calendar.getInstance().getTime()), emailDatasink,
            new DatasinkEmailConfig() {
               
               @Override
               public String getFilename() {
                  return "reportserver-email-datasink-test.txt";
               }
               
               @Override
               public boolean isSendSyncEmail() {
                  return true;
               }
               
               @Override
               public String getSubject() {
                  return emailText;
               }
               
               @Override
               public List<User> getRecipients() {
                  return Arrays.asList(authenticatorServiceProvider.get().getCurrentUser());
               }
               
               @Override
               public String getBody() {
                  return emailText + " " + dateFormat.format(Calendar.getInstance().getTime());
               }
            });
   }

   @Override
   public Optional<EmailDatasink> getDefaultDatasink() {
      return defaultDatasinkProvider.get();
   }

   @Override
   public String getDatasinkPropertyName() {
      return "email";
   }

   @Override
   public StorageType getStorageType() {
      return StorageType.EMAIL;
   }

   @Override
   public StorageType getSchedulingStorageType() {
      return StorageType.EMAIL_SCHEDULING;
   }

}
