package net.datenwerke.rs.dropbox.service.dropbox;

import java.util.Optional;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;
import net.datenwerke.rs.dropbox.service.dropbox.definitions.DropboxDatasink;

@ImplementedBy(DummyDropboxServiceImpl.class)
public interface DropboxService extends BasicDatasinkService {

   /**
    * Gets the default datasink configured in the datasinks.cf configuration file.
    * 
    * @return the default datasink
    */
   Optional<DropboxDatasink> getDefaultDatasink();

}
