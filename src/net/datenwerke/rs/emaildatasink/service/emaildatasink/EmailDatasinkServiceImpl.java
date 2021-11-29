package net.datenwerke.rs.emaildatasink.service.emaildatasink;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Provider;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.mail.MailBuilderFactory;
import net.datenwerke.rs.core.service.mail.MailService;
import net.datenwerke.rs.core.service.mail.SimpleAttachment;
import net.datenwerke.rs.core.service.mail.SimpleMail;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.annotations.DefaultEmailDatasink;
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
   public void exportIntoDatasink(Object report, EmailDatasink emailDatasink, String subject, String body,
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
   public void testDatasink(EmailDatasink emailDatasink) throws IOException {
      if (!datasinkServiceProvider.get().isEnabled(this))
         throw new IllegalStateException("Email datasink is disabled");

      String emailText = "ReportServer Email Datasink Test";
      exportIntoDatasink(emailText + " " + dateFormat.format(Calendar.getInstance().getTime()), emailDatasink,
            emailText, emailText + " " + dateFormat.format(Calendar.getInstance().getTime()),
            Arrays.asList(authenticatorServiceProvider.get().getCurrentUser()), "reportserver-email-datasink-test.txt",
            true);
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
