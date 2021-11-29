package net.datenwerke.rs.localfsdatasink.service.localfsdatasink;

import java.util.Optional;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;
import net.datenwerke.rs.localfsdatasink.service.localfsdatasink.definitions.LocalFileSystemDatasink;

@ImplementedBy(DummyLocalFileSystemServiceImpl.class)
public interface LocalFileSystemService extends BasicDatasinkService {

   /**
    * Gets the default datasink configured in the datasinks.cf configuration file.
    * 
    * @return the default datasink
    */
   Optional<LocalFileSystemDatasink> getDefaultDatasink();

}