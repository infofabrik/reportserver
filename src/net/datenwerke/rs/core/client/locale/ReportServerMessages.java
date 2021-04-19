package net.datenwerke.rs.core.client.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface ReportServerMessages extends Messages {

	public final static ReportServerMessages INSTANCE = GWT.create(ReportServerMessages.class);

	String accessRsPermission();

	String accessRsPermissionPermissionModuleDescription();
	
	String htmlErrorPageTitle();
	String htmlErrorPageHeading();
	String htmlErrorInstructions();
	String htmlErrorPageDetailSectionHeader();
	
}