package net.datenwerke.rs.remoteserver.service.remoteservermanager.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface RemoteServerManagerMessages extends Messages {
   
   public final static RemoteServerManagerMessages INSTANCE = LocalizationServiceImpl
         .getMessages(RemoteServerManagerMessages.class);

   String historyUrlBuilderName();

   String historyUrlBuilderIcon();

   String remoteServerFolderTypeName();
   
   String remoteRsServer();
   
   String remoteRsServers();

   String totalRemoteServers();

}