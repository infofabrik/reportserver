package net.datenwerke.rs.scheduleasfile.client.scheduleasfile.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface ScheduleAsFileMessages extends Messages {

	public final static ScheduleAsFileMessages INSTANCE = GWT.create(ScheduleAsFileMessages.class);
	
	String askStoreAsFile();

	String folder();

	String teamspace();

	String executedReportNodeType();

	String exportToTeamSpaceLabel();
	String exportTypeLabel();
	String nameLabel();
	String descriptionLabel();
	
	String appName();
	String appDescription();

	String teamspaceSchedulerToolTip();

	String noFolderSelected();

	String executedReport();

	String dataSent();
}
