package net.datenwerke.rs.core.client.remoteservermanager.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface RemoteServerMessages extends Messages {

   public final static RemoteServerMessages INSTANCE = GWT.create(RemoteServerMessages.class);

   String editFolder();
   
   String remoteServers();

   String remoteServerPermissionModuleDescription();
   
}