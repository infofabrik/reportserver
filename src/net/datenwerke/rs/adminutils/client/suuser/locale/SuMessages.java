package net.datenwerke.rs.adminutils.client.suuser.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface SuMessages extends Messages {

	public final static SuMessages INSTANCE = GWT.create(SuMessages.class);
	
	String suCommandName();

	String suPermissionModuleDescription();

	String suPromptHeader();

	String userLabel();
	
}