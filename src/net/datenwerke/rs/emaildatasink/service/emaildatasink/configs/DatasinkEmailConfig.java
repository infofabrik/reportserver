package net.datenwerke.rs.emaildatasink.service.emaildatasink.configs;

import java.util.List;

import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkFilenameConfig;
import net.datenwerke.security.service.usermanager.entities.User;

public interface DatasinkEmailConfig extends DatasinkFilenameConfig {

   String getSubject();

   String getBody();

   List<User> getRecipients();

   boolean isSendSyncEmail();

}
