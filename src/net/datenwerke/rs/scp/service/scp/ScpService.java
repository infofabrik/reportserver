package net.datenwerke.rs.scp.service.scp;

import java.util.Optional;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;
import net.datenwerke.rs.scp.service.scp.definitions.ScpDatasink;

@ImplementedBy(DummyScpServiceImpl.class)
public interface ScpService extends BasicDatasinkService {

   /**
    * Gets the default datasink configured in the datasinks.cf configuration file.
    * 
    * @return the default datasink
    */
   Optional<ScpDatasink> getDefaultDatasink();

}
