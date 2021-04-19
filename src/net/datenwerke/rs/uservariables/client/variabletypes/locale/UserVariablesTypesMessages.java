package net.datenwerke.rs.uservariables.client.variabletypes.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface UserVariablesTypesMessages extends Messages {

	public final static UserVariablesTypesMessages INSTANCE = GWT.create(UserVariablesTypesMessages.class);
	
	String textVariable();

	String listVariable();

	String noEditTitle();
	String noEditMessage();
	

}
