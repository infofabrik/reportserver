package net.datenwerke.rs.ftp.service.ftp;

import java.io.IOException;

import net.datenwerke.rs.core.service.datasinkmanager.HostDatasink;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public interface FtpSenderService {

   void sendToFtpServer(StorageType storageType, Object data, HostDatasink basicDatasink,
         DatasinkConfiguration config) throws IOException;

}
