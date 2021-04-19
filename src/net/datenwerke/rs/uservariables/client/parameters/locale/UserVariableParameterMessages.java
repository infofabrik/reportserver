package net.datenwerke.rs.uservariables.client.parameters.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface UserVariableParameterMessages extends Messages {

	public final static UserVariableParameterMessages INSTANCE = GWT.create(UserVariableParameterMessages.class);
	
	String userVariable();
	
}
