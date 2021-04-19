package net.datenwerke.rs.core.client.datasinkmanager.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface DatasinksMessages extends Messages {

	public final static DatasinksMessages INSTANCE = GWT.create(DatasinksMessages.class);
	
	String editFolder();

	String datasink();

	String datasinks();

	String editDatasink();

	String datasinkPermissionModuleDescription();
	
	String testFailed();

   String testDatasink();

   String testSuccess();

   String pleaseWait();

   String testingTitle();

   String testingProgressMessage();

   String success();
   
   String authenticationType();
   String userPasswordAuthenticationType();
   String publicKeyAuthenticationType();
   String privateKey();
   String privateKeyPassphrase();
   String clearPassphrase();
   String importConfigFailureNoParent();
   String importWhereTo();
   String importMainPropertiesDescription();
   String importMainPropertiesHeadline();
   
   String localFileSystem();
	
}