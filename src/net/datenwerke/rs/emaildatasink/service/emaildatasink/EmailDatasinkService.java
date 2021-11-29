package net.datenwerke.rs.emaildatasink.service.emaildatasink;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.EmailDatasink;
import net.datenwerke.security.service.usermanager.entities.User;

public interface EmailDatasinkService extends BasicDatasinkService {

   /**
    * Sends a report to an email SMTP server defined in a given
    * {@link EmailDatasink} datasink.
    * 
    * @param report        the report to send. May be a String or a byte array
    * @param emailDatasink defines the email SMTP server
    * @param subject       the subject of the email
    * @param body          the content of the email
    * @param recipients    the recipients of the email. Only recipients containing
    *                      an email address are used
    * @param filename      filename to use for the report email attachment
    * @param sendSyncEmail {@code true} if the email should be sent synchronously.
    *                      {@code false} if the email should be sent
    *                      asynchronously.
    * @throws IOException if an I/O error occurs
    */
   void exportIntoDatasink(Object report, EmailDatasink emailDatasink, String subject, String body,
         List<User> recipients, String filename, boolean sendSyncEmail) throws IOException;

   /**
    * Issues an email SMTP test request by creating a simple text file and sending
    * it to the specified SMTP server of the datasink.
    * 
    * @param emailDatasink the {@link EmailDatasink} to test
    * @throws IOException if an I/O error occurs
    */
   void testDatasink(EmailDatasink emailDatasink) throws IOException;

   /**
    * Returns the default email datasink, used for sending emails without using the
    * old mail.cf configuration file but an existing email SMTP datasink instead.
    * 
    * @return the default email datasink
    */
   Optional<EmailDatasink> getDefaultDatasink();
}