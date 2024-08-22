package net.datenwerke.rs.core.service.mail;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;

import net.datenwerke.gf.service.tempfile.TempFileService;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.EmailDatasink;
import net.datenwerke.rs.utils.misc.MimeUtils;
import net.datenwerke.rs.utils.zip.ZipUtilsService;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * Builder class for constructing an email step-by-step by providing the
 * necessary elements using the builder pattern. By default, the email is sent
 * using the ReportServer internal email settings. If you need to send the email
 * to a given {@link EmailDatasink}, you can use
 * {@link #withEmailDatasink(EmailDatasink)}. A typical email can be constructed
 * and sent as shown below.
 * 
 * <pre>
 * {
 *    &#64;code
 *    SimpleMail mail = mailBuilderFactory.create("my subject text", "my body text", Arrays.asList(currentUser))
 *          .withEmailDatasink(emailDatasink).withFileAttachments(files).withZippedAttachments("myAttachments.zip")
 *          .build();
 *    mailService.sendMail(mail);
 * }
 * </pre>
 * 
 * where
 * <ul>
 * <li>{@code mailBuilderFactory} denotes the {@link MailBuilderFactory} for
 * creating the mail builder</li>
 * <li>{@code emailDatasink} denotes the {@link EmailDatasink} used for sending
 * the email</li>
 * <li>{@code currentUser} denotes the current {@link User} as the e-mail
 * recipient</li>
 * <li>{@code files} denotes the list of attachments to add</li>
 * <li>Finally, the e-mail is sent using the {@link MailService}</li>
 * </ul>
 */
public class MailBuilder {

   private final String subject;
   private final String body;
   private final List<User> recipients;
   private final InternetAddress from;

   private final Provider<MailService> mailServiceProvider;
   private final Provider<TempFileService> tempFileServiceProvider;
   private final Provider<ZipUtilsService> zipServiceProvider;
   private final Provider<MimeUtils> mimeUtilsProvider;

   private boolean template = false;
   private Optional<Map<String, Object>> replacements = Optional.empty();
   private Optional<String> zipFilename = Optional.empty();
   private Optional<List<SimpleAttachment>> attachments = Optional.empty();

   private Optional<EmailDatasink> emailDatasink = Optional.empty();

   @Inject
   public MailBuilder(Provider<MailService> mailServiceProvider, Provider<TempFileService> tempFileServiceProvider,
         Provider<ZipUtilsService> zipServiceProvider, Provider<MimeUtils> mimeUtilsProvider,
         @Assisted("subject") String subject, @Assisted("body") String body, @Assisted List<User> recipients, @Assisted InternetAddress from) {
      this.mailServiceProvider = mailServiceProvider;
      this.tempFileServiceProvider = tempFileServiceProvider;
      this.zipServiceProvider = zipServiceProvider;
      this.mimeUtilsProvider = mimeUtilsProvider;

      this.subject = subject;
      this.body = body;
      this.recipients = recipients;
      this.from = from;
   }

   /**
    * Specifies that the email contains a template and specifies which replacements
    * should be used for template substitution.
    * 
    * @param replacements the replacements for template substitution
    * @return the current state of {@link MailBuilder}
    */
   public MailBuilder withTemplateReplacements(Map<String, Object> replacements) {
      template = true;
      this.replacements = Optional.of(replacements);
      return this;
   }

   /**
    * Adds a list of files as email attachments.
    * 
    * @param attachments the list of files to add as email attachments.
    * @return the current state of {@link MailBuilder}
    */
   public MailBuilder withFileAttachments(final List<Path> attachments) {
      final MimeUtils mimeUtils = mimeUtilsProvider.get();
      this.attachments = Optional.of(attachments.stream().map(file -> {
         String filename = file.getFileName().toString();
         return new SimpleAttachment(file, mimeUtils.getMimeTypeByExtension(filename), filename);
      }).collect(toList()));
      return this;
   }

   /**
    * Adds a list of attachments. These can contain byte arrays or {@link Path}s.
    * 
    * @param attachments the attachments as byte arrays or {@link Path}s
    * @return the current state of {@link MailBuilder}
    */
   public MailBuilder withAttachments(final List<SimpleAttachment> attachments) {
      this.attachments = Optional.of(attachments);
      return this;
   }

   /**
    * Specifies that the attachments should be zipped using the provided filename.
    * 
    * @param zipFilename the zip filename.
    * @return the current state of {@link MailBuilder}
    */
   public MailBuilder withZippedAttachments(String zipFilename) {
      Objects.requireNonNull(zipFilename);
      this.zipFilename = Optional.of(zipFilename.endsWith(".zip") ? zipFilename : zipFilename + ".zip");
      return this;
   }

   /**
    * Specifies that the given {@link EmailDatasink} should be used instead of the
    * default (internal) email settings.
    * 
    * @param emailDatasink the {@link EmailDatasink} to use.
    * @return the current state of {@link MailBuilder}
    */
   public MailBuilder withEmailDatasink(EmailDatasink emailDatasink) {
      Objects.requireNonNull(emailDatasink);
      this.emailDatasink = Optional.of(emailDatasink);
      return this;
   }

   /**
    * Builds the email using the previously specified options.
    * 
    * @return the constructed email.
    * @throws IOException if an I/O error occurs
    * @throws MessagingException if sender can not be set
    */
   public SimpleMail build() throws IOException, MessagingException {
      if (recipients.isEmpty())
         throw new IllegalArgumentException("Recipient list is empty");

      if (recipients.stream().anyMatch(u -> null == u.getEmail() || "".equals(u.getEmail())))
         throw new IllegalArgumentException("Recipients must have email defined");

      if (attachments.isPresent()) {
         if (zipFilename.isPresent())
            return buildMailWithZippedAttachments();
         else
            return buildMailWithAttachments();
      } else
         return buildMailWithoutAttachments();

   }

   private MailTemplate createMailTemplate() {
      MailTemplate mailTemplate = new MailTemplate();
      mailTemplate.setSubjectTemplate(subject);
      mailTemplate.setMessageTemplate(body);
      mailTemplate.setDataMap(replacements.orElseThrow(IllegalStateException::new));
      return mailTemplate;
   }

   private SimpleMail buildMailWithoutAttachments() throws IOException, MessagingException {

      SimpleMail mail = null;

      if (template) {
         MailTemplate mailTemplate = createMailTemplate();
         mail = mailServiceProvider.get().newTemplateMail(emailDatasink, mailTemplate);
         mail.setFrom(from, true);
      } else {
         mail = mailServiceProvider.get().newSimpleMail(emailDatasink);
         mail.setSubject(subject);
         mail.setText(body);
         mail.setFrom(from, true);
      }

      configureRecipients(mail);
      return mail;
   }

   private SimpleMail buildMailWithAttachments() throws IOException, MessagingException {
      SimpleMail mail = null;

      if (template) {
         MailTemplate mailTemplate = createMailTemplate();
         mail = mailServiceProvider.get().newTemplateMail(emailDatasink, mailTemplate,
               attachments.orElseThrow(IllegalStateException::new).toArray(new SimpleAttachment[] {}));
         mail.setFrom(from, true);
      } else {
         mail = mailServiceProvider.get().newSimpleMail(emailDatasink);
         mail.setSubject(subject);
         mail.setContent(body, attachments.orElseThrow(IllegalStateException::new).toArray(new SimpleAttachment[] {}));
         mail.setFrom(from, true);
      }

      configureRecipients(mail);

      return mail;
   }

   private SimpleMail buildMailWithZippedAttachments() throws IOException, MessagingException {
      final Path tmpFile = tempFileServiceProvider.get().createTempFile();

      try (final OutputStream out = Files.newOutputStream(tmpFile)) {
         zipServiceProvider.get().createZipFromEmailAttachments(attachments.get(), out);
         SimpleAttachment attachment = new SimpleAttachment(tmpFile,
               mimeUtilsProvider.get().getMimeTypeByExtension(zipFilename.orElseThrow(IllegalStateException::new)),
               zipFilename.orElseThrow(IllegalStateException::new));

         SimpleMail mail = null;

         if (template) {
            MailTemplate mailTemplate = createMailTemplate();
            mail = mailServiceProvider.get().newTemplateMail(emailDatasink, mailTemplate, attachment);
            mail.setFrom(from, true);
         } else {
            mail = mailServiceProvider.get().newSimpleMail(emailDatasink);
            mail.setSubject(subject);
            mail.setContent(body, attachment);
            mail.setFrom(from, true);
         }

         configureRecipients(mail);

         return mail;
      }
   }

   private void configureRecipients(SimpleMail mail) {
      mail.setToRecipients(
            recipients.stream().filter(user -> null != user.getEmail()).map(User::getEmail).collect(toList()));
   }

}
