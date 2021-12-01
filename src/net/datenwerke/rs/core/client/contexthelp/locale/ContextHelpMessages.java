package net.datenwerke.rs.core.client.contexthelp.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface ContextHelpMessages extends Messages {

	public final static ContextHelpMessages INSTANCE = GWT.create(ContextHelpMessages.class);
	
	String windowTitlePrefix();
}
