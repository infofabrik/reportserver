package net.datenwerke.rs.remoteserver.client.remoteservermanager.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface RemoteServerManagerMessages extends Messages {

   public final static RemoteServerManagerMessages INSTANCE = GWT.create(RemoteServerManagerMessages.class);
   
   public String remoteServers();

   public String remoteServerPermissionModuleDescription();

   public String editFolder();

   public String editRemoteServer();

   public String useDefaultFailureMessage();

   public String useDefaultSuccessMessage();

   public String useDefaultFailureTitle();
}