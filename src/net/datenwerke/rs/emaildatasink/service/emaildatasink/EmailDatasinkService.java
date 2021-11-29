package net.datenwerke.rs.emaildatasink.service.emaildatasink;

import java.util.Optional;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.exceptions.DatasinkExportException;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.EmailDatasink;

public interface EmailDatasinkService extends BasicDatasinkService {

   /**
    * Sends a report to an email SMTP server defined in a given
    * {@link EmailDatasink} datasink.
    * 
    * @param report        the report to send. May be a String or a byte array
    * @param emailDatasink defines the email SMTP server
    * @param config        configuration of the export
    * @throws DatasinkExportException if an error occurs during datasink export
    */
   void exportIntoDatasink(Object report, EmailDatasink emailDatasink, DatasinkConfiguration config)
         throws DatasinkExportException;

   /**
    * Issues an email SMTP test request by creating a simple text file and sending
    * it to the specified SMTP server of the datasink.
    * 
    * @param emailDatasink the {@link EmailDatasink} to test
    * @throws DatasinkExportException if an error occurs during datasink export
    */
   void testDatasink(EmailDatasink emailDatasink) throws DatasinkExportException;

   /**
    * Returns the default email datasink, used for sending emails without using the
    * old mail.cf configuration file but an existing email SMTP datasink instead.
    * 
    * @return the default email datasink
    */
   Optional<EmailDatasink> getDefaultDatasink();
}