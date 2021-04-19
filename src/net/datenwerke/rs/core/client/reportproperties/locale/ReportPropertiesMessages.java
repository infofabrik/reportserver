package net.datenwerke.rs.core.client.reportproperties.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface ReportPropertiesMessages extends Messages {
	
	public final static ReportPropertiesMessages INSTANCE = GWT.create(ReportPropertiesMessages.class);
	
	String header();
	String description();
	String gridNameHeader();
	String gridValueHeader();
	String inheritedReportProperties();
}
