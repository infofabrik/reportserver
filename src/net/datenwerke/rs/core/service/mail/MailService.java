package net.datenwerke.rs.core.service.mail;

import java.util.List;
import java.util.Optional;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;

import net.datenwerke.rs.core.service.mail.MailServiceImpl.MailSupervisor;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.EmailDatasink;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * 
 *
 */
public interface MailService {

   /**
    * Creates and returns a new instance of {@link SimpleMail}
    * 
    * @return If there is a CA certificate a new instance of
    *         {@link SimpleCryptoMail}, else a new instance of {@link SimpleMail}
    */
   public SimpleMail newSimpleMail();

   public SimpleMail newSimpleMail(Optional<EmailDatasink> emailDatasink);

   /**
    * Creates and returns a new instance of {@link SimpleMail} configured with the
    * given template
    * 
    * @param template The {@link MailTemplate} to use
    * @return A new instance of {@link SimpleMail} using the given
    *         {@link MailTemplate}
    */
   public SimpleMail newTemplateMail(MailTemplate template, SimpleAttachment... attachments);

   public SimpleMail newTemplateMail(Optional<EmailDatasink> emailDatasink, MailTemplate template,
         SimpleAttachment... attachments);

   /**
    * Spawns a new worker which then sends the message
    * 
    * @param message The {@link MimeMessage} to send
    */
   public void sendMail(MimeMessage message);

   public void sendMail(Optional<EmailDatasink> emailDatasink, MimeMessage message);

   /**
    * Sends a mail synchronously
    * 
    * @param message The {@link MimeMessage} to send
    */
   void sendMailSync(MimeMessage message);

   void sendMailSync(Optional<EmailDatasink> emailDatasink, MimeMessage message);

   void sendMailSync(MimeMessage message, MailSupervisor supervisor);

   void sendMailSync(Optional<EmailDatasink> emailDatasink, MimeMessage message, MailSupervisor supervisor);

   void sendMail(MimeMessage message, MailSupervisor supervisor);

   void sendMail(Optional<EmailDatasink> emailDatasink, MimeMessage message, MailSupervisor supervisor);

   List<Address> getEmailList(List<User> users) throws AddressException;

}
