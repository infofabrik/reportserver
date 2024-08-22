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
   
   String testFailed();

   String success();

   String testSuccess();

   String pleaseWait();

   String testingTitle();

   String testingProgressMessage();

   String newLdapServer();

   String editLdapServer();

   String disabled();

   String provider();

   String security();

   String encryption();

   String base();

   String filter();

   String externalDirectory();

   String writeProtection();

   String logResultingTree();

   String flattenTree();

   String objectClass();

   String attributes();

   String name();

   String member();

   String additional();

   String organizationalUnit();

   String group();

   String user();
   
}