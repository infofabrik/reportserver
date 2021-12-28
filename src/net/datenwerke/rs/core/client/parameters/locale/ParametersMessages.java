package net.datenwerke.rs.core.client.parameters.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface ParametersMessages extends Messages {

	public final ParametersMessages INSTANCE = GWT.create(ParametersMessages.class);
	
	String parameter();
	String divider();
	
	String mainPanelView_headline();
	String parameterMoved();
	String parameterRemoved();
	String parameterChanged();
	String keyLabel();
	String specialProperties();
	String parameterProperties();
	String parameterAdded();
	String key();
	String dependsOn();
	String generalPoperties();
	String editParameter();
	String reallyRemoveAllText();
	
	String propertyHidden();
	String propertyEditable();
	
	String setDefaultValues();
	
	String parameterInstancesUpdated();
	String updateAllInstances();
	String updateInstances();
	
	String updateInstancesMsg();
	String updateInstancesMsgTitle();

	String parameterDuplicated();
	String propertyDisplayInline();

	String optionsHeader();
	
	String reallyRemoveParameterText();
	String reallyRemoveParametersText(int size);
	
	String noParametersSelected();
	String propertyMandatory();
	String mandatoryParameterNotSelected(String name);
	String propertyLabelWidth();


}
