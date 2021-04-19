package net.datenwerke.rs.eximport.client.eximport.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface ExImportMessages extends Messages{

	public static final ExImportMessages INSTANCE = GWT.create(ExImportMessages.class);
	
	String importAdminModuleText();

	String noImportData();
	
	String beginImportButtonLabel();
	
	String uploadImportDataLabel();
	
	String importAbortButtonLabel();

	String importSubmitButtonLabel();
	
	String storeConfiguration();
	String performImport();
	
	String importSuccess();
	
	String mainOptionsButtonLabel();
	String importPanelHeader();
	
	String importMainProperties();
	String importItems();
	
	String selectChildrenLabel();
	String deselectChildrenLabel();
	
	String exportGroup();
	String quickExportText();
	
	String exportSuccededTitle();
	String exportSuccededMessage();
	String quickExportDisplayDirectLabel();
	String quickExportDownloadLabel();
	
	String quickExportProgressTitle();
	String quickExportProgressText();
	
	String uploadImportFileDataLabel();
	String errorImportBeginMessage();
	
	String clearConfigTitle();
	String clearConfigMsg();
	
	String importResetButtonLabel();

	String exportSecurityLabel();
	String exportSecurityDescription();

	String importSecurityLabel();
	String importSecurityDescription();
	
	String exportWait();
	String exportWaitTitle();
}
