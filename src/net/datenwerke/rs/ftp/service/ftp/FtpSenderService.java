package net.datenwerke.rs.ftp.service.ftp;

import java.io.IOException;

import net.datenwerke.rs.core.service.datasinkmanager.HostDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public interface FtpSenderService {

   void sendToFtpServer(StorageType storageType, Object report, HostDatasink basicDatasink, String filename,
         String folder) throws IOException;
   
}
