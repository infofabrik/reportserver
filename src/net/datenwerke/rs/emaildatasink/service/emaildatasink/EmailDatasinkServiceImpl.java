package net.datenwerke.rs.emaildatasink.service.emaildatasink;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Provider;

import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
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
import net.datenwerke.security.service.usermanager.entities.User;

public class EmailDatasinkServiceImpl implements EmailDatasinkService {

   private final Provider<MimeUtils> mimeUtilsProvider;

   public static final int DEFAULT_BUFFER_SIZE = 8192;

   private final Provider<MailBuilderFactory> mailBuilderFactoryProvider;
   private final Provider<MailService> mailServiceProvider;
   private final Provider<Optional<EmailDatasink>> defaultDatasinkProvider;

   @Inject
   public EmailDatasinkServiceImpl(Provider<MailBuilderFactory> mailBuilderFactoryProvider,
         Provider<MailService> mailServiceProvider, Provider<MimeUtils> mimeUtilsProvider,
         @DefaultEmailDatasink Provider<Optional<EmailDatasink>> defaultDatasinkProvider) {
      this.mailBuilderFactoryProvider = mailBuilderFactoryProvider;
      this.mailServiceProvider = mailServiceProvider;
      this.mimeUtilsProvider = mimeUtilsProvider;
      this.defaultDatasinkProvider = defaultDatasinkProvider;
   }

   @Override
   public void doExportIntoDatasink(Object report, User user, DatasinkDefinition datasink, DatasinkConfiguration config)
         throws DatasinkExportException {
      Objects.requireNonNull(datasink, "datasink is null!");
      if (!(config instanceof DatasinkEmailConfig))
         throw new IllegalStateException("Not a DatasinkEmailConfig config");
      if (!(datasink instanceof EmailDatasink))
         throw new IllegalStateException("Not an Email datasink");

      DatasinkEmailConfig emailConfig = (DatasinkEmailConfig) config;
      EmailDatasink emailDatasink = (EmailDatasink) datasink;

      Objects.requireNonNull(emailConfig.getFilename());

      try {
         SimpleMail mail = mailBuilderFactoryProvider.get()
               .create(emailConfig.getSubject(), emailConfig.getBody(), emailConfig.getRecipients())
               .withEmailDatasink(emailDatasink)
               .withAttachments(Arrays.asList(new SimpleAttachment(report,
                     mimeUtilsProvider.get().getMimeTypeByExtension(emailConfig.getFilename()),
                     emailConfig.getFilename())))
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
