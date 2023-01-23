package net.datenwerke.rs.adminutils.service.logs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.mail.MessagingException;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.io.input.ReversedLinesFileReader;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.localization.RemoteMessageService;
import net.datenwerke.rs.adminutils.service.locale.AdminUtilsMessages;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.GeneralInfoService;
import net.datenwerke.rs.base.service.parameterreplacements.provider.UserForJuel;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.core.service.mail.MailBuilderFactory;
import net.datenwerke.rs.core.service.mail.MailService;
import net.datenwerke.rs.core.service.mail.SimpleMail;
import net.datenwerke.rs.core.service.reportserver.ReportServerService;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.usermanager.entities.User;

public class LogFilesServiceImpl implements LogFilesService {

   private static final int NUMBER_OF_LINES = 500;

   private final Provider<ConfigService> configServiceProvider;
   private final Provider<AuthenticatorService> authenticatorServiceProvider;
   private final Provider<MailService> mailServiceProvider;
   private final Provider<MailBuilderFactory> mailBuilderFactoryProvider;
   private final Provider<RemoteMessageService> remoteMessageServiceProvider;
   private final Provider<GeneralInfoService> generalInfoServiceProvider;

   private final String dateFormat = "yyyy-MM-dd-HH-mm-ss";

   @Inject
   public LogFilesServiceImpl(
         Provider<ConfigService> configServiceProvider,
         Provider<AuthenticatorService> authenticatorServiceProvider, 
         Provider<MailService> mailServiceProvider,
         Provider<MailBuilderFactory> mailBuilderFactoryProvider,
         Provider<RemoteMessageService> remoteMessageServiceProvider,
         Provider<GeneralInfoService> generalInfoServiceProvider
         ) {
      this.configServiceProvider = configServiceProvider;
      this.authenticatorServiceProvider = authenticatorServiceProvider;
      this.mailServiceProvider = mailServiceProvider;
      this.mailBuilderFactoryProvider = mailBuilderFactoryProvider;
      this.remoteMessageServiceProvider = remoteMessageServiceProvider;
      this.generalInfoServiceProvider = generalInfoServiceProvider;
   }

   @Override
   public String getLogDirectory() {
      return configServiceProvider.get().getConfigFailsafe(ReportServerService.CONFIG_FILE).getString("logdir",
            generalInfoServiceProvider.get().getCatalinaHome() + "/logs");
   }

   @Override
   public List<String> readLastLines(String filename) throws IOException {
      Path logPath = Paths.get(getLogDirectory());
      Path file = Paths.get(logPath.toAbsolutePath() + "/" + filename);

      checkLogFiles(Arrays.asList(file));

      List<String> lines = new ArrayList<>();

      ReversedLinesFileReader object = new ReversedLinesFileReader(file.toFile());

      for (int i = 0; i < NUMBER_OF_LINES; i++) {
         String line = object.readLine();
         if (line == null)
            break;
         lines.add(line);
      }

      Collections.reverse(lines);

      return lines;
   }

   @Override
   public void emailLogFiles(List<Path> files, String filter) throws MessagingException, IOException {
      checkLogFiles(files);

      final Configuration config = configServiceProvider.get().getConfigFailsafe("security/notifications.cf");

      User currentUser = authenticatorServiceProvider.get().getCurrentUser();
      if (null == currentUser.getEmail() || currentUser.getEmail().isEmpty())
         throw new IllegalArgumentException("Current user has empty email");

      String defaultSubject = AdminUtilsMessages.INSTANCE.emailLogFilesSubject();
      String defaultBody = AdminUtilsMessages.INSTANCE.emailLogFilesIntro() + "\n\n" + currentUser.getUsername()
            + "\n\n" + AdminUtilsMessages.INSTANCE.emailLogFilesEnd();

      SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
      String filenameDesc = "ReportServer-logs-" + sdf.format(new Date());
      String subject = config.getString("logfiles.email.subject", defaultSubject);
      String body = config.getString("logfiles.email.text", defaultBody);

      Map<String, Object> replacements = new HashMap<>();
      String currentLanguage = LocalizationServiceImpl.getLocale().getLanguage();
      replacements.put("msgs", remoteMessageServiceProvider.get().getMessages(currentLanguage));
      replacements.put("user", UserForJuel.createInstance(currentUser));
      replacements.put("filter", filter);

      SimpleMail mail = mailBuilderFactoryProvider.get()
            .create(subject, body, Arrays.asList(currentUser))
            .withFileAttachments(files)
            .withZippedAttachments(filenameDesc + ".zip")
            .withTemplateReplacements(replacements)
            .build();

      mailServiceProvider.get().sendMail(mail);
   }

   @Override
   public void checkLogFiles(final List<Path> files) throws IOException {

      Path logPath = Paths.get(getLogDirectory());
      if (!Files.exists(logPath))
         throw new IllegalArgumentException("no valid log file directory configured");

      if (files.stream().anyMatch(f -> !Files.exists(f) || Files.isDirectory(f)))
         throw new IllegalArgumentException("Files passed are not valid log files");

      try (Stream<Path> stream = Files.list(logPath)) {
         if (!stream.anyMatch(f -> files.contains(f)))
            throw new IllegalArgumentException("Files passed are not valid log files");
      }

   }

}
