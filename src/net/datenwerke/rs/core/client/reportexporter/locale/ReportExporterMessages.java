package net.datenwerke.rs.core.client.reportexporter.locale;


import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface ReportExporterMessages extends Messages {

	public final static ReportExporterMessages INSTANCE = GWT.create(ReportExporterMessages.class);
	
	String executing();

	String Export2Excel();

	String export2HTML();
	
	String export2JSON();
	String export2JSONC();
	
	String jsonc();

	String Export2PDF();
	
	String Export2RTF();
	
	String exportMenuBtnLabel();
	String exportReport();
	
	String exportReportTo(String exportTitle);

	String exportTypeLabel();
	
	String exportViaMailLabel();
	String formatConfigLabel();
	
	String messageSend();
	
	String selectUserLabel();
	String sendToLabel();
	String subjectLabel();
	
	String messageLabel();

	String exportTypeNotConfigured();

	String reportNotExportable();

	String Export2Doc();

	String export2Text();
	String export2XML();

	String reportIsBeingExportedTitle();
	
	String reportIsBeingExportedMsg(String outputFormat);
	String reportIsBeingExportedMsgSkipDownload(String outputFormat);
	
	String reportSuccessfullyExportedTitle();
	String reportSuccessfullyExported();
	
	String reportLoadingWait();

}
