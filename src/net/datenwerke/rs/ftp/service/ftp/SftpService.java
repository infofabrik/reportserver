package net.datenwerke.rs.ftp.service.ftp;

import java.util.Optional;

import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;
import net.datenwerke.rs.ftp.service.ftp.definitions.SftpDatasink;

public interface SftpService extends BasicDatasinkService {

   /**
    * Gets the default datasink configured in the datasinks.cf configuration file.
    * 
    * @return the default datasink
    */
   Optional<SftpDatasink> getDefaultSftpDatasink();

}
