package net.datenwerke.rs.uservariables.client.uservariables.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface UserVariablesMessages extends Messages {
	
	public final static UserVariablesMessages INSTANCE = GWT.create(UserVariablesMessages.class);
	
	public String confirmRemoveAllHeader();
	public String confirmRemoveAllText();
	public String confirmRemoveText();

	public String editVariable();

	public String mainPanelView_description();

	public String mainPanelView_header();

	public String mainPanelView_inheritedDescription();

	public String moduleName();


	public String removedVariable();

	public String uservariableManagamentHeading();

	public String userVariableManager();

	public String userVariablesegnericAdminHeading();

	public String userVariablesGenericAdmindescription();

	public String variableCreated();

	public String variableDeleted();

	public String viewDescription();
	public String viewHeadline();
	
	public String userVariablesParameterText();

	public String listVariableText();
	public String stringVariableText();
	public String definedAt();
}
