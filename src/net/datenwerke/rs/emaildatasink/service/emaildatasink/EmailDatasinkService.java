package net.datenwerke.rs.emaildatasink.service.emaildatasink;

import java.util.Optional;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.EmailDatasink;

public interface EmailDatasinkService extends BasicDatasinkService {

   /**
    * Returns the default email datasink, used for sending emails without using the
    * old mail.cf configuration file but an existing email SMTP datasink instead.
    * 
    * @return the default email datasink
    */
   Optional<EmailDatasink> getDefaultDatasink();
}