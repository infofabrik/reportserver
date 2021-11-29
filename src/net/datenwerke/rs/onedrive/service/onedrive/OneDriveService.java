package net.datenwerke.rs.onedrive.service.onedrive;

import java.util.Optional;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;
import net.datenwerke.rs.onedrive.service.onedrive.definitions.OneDriveDatasink;

@ImplementedBy(DummyOneDriveServiceImpl.class)
public interface OneDriveService extends BasicDatasinkService {

   /**
    * Gets the default datasink configured in the datasinks.cf configuration file.
    * 
    * @return the default datasink
    */
   Optional<OneDriveDatasink> getDefaultDatasink();
}