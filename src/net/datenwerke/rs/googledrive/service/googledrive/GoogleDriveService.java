package net.datenwerke.rs.googledrive.service.googledrive;

import java.util.Optional;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;
import net.datenwerke.rs.googledrive.service.googledrive.definitions.GoogleDriveDatasink;

@ImplementedBy(DummyGoogleDriveServiceImpl.class)
public interface GoogleDriveService extends BasicDatasinkService {

   /**
    * Gets the default datasink configured in the datasinks.cf configuration file.
    * 
    * @return the default datasink
    */
   Optional<GoogleDriveDatasink> getDefaultDatasink();

}
