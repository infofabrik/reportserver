package net.datenwerke.rs.remoteaccess.client.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface RemoteAccessMessages extends Messages {
	
	public final static RemoteAccessMessages INSTANCE = GWT.create(RemoteAccessMessages.class);

	
	String sftpPermission();

	String sftpPermissionPermissionModuleDescription();
}
