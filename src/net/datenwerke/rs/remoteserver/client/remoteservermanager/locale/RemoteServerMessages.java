package net.datenwerke.rs.remoteserver.client.remoteservermanager.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface RemoteServerMessages extends Messages {

   public final static RemoteServerMessages INSTANCE = GWT.create(RemoteServerMessages.class);
   
   String remoteServers();

   String remoteServerPermissionModuleDescription();

   String editFolder();

   String editRemoteRsServer();

   String importMainPropertiesDescription();

   String importMainPropertiesHeadline();

   String importWhereTo();

   String importConfigFailureNoParent();
   
   String newRemoteRsServer();
}