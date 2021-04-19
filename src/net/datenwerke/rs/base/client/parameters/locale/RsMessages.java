package net.datenwerke.rs.base.client.parameters.locale;


import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface RsMessages extends Messages {

	public final static RsMessages INSTANCE = GWT.create(RsMessages.class);
	
	String datasource();
	String datasourceParameter();
	String defaultValue();
	String displayText();
	String dropdown();
	String format();
	String generalProperties();
	String headline();
	String mode();
	String multiSelect();
	String noDataSelected();
	String popup();
	String returnType();
	String seletionMode();
	String separator();
	String singleSelect();
	String singleSeletionMode();
	String multiSeletionMode();
	String text();
	String useNowAsDefault();
	String defaultFormula();
	String defaultValues();
	String radio();
	String checkbox();
	String listbox();
	
	
	String boxLayoutPackColSize();
	String boxLayoutPackMode();
	String columns();
	String packages();
	String boxLayoutMode();
	String boxLayoutLrTd();
	String boxLayoutTdLr();
	String datasourceParameterPostProcess();
	String datasourceNotConfigured();
	
	String validatorRegexLabel();
	String badRegex(String regex);
	String invalidParameter(String name);

	String dateTimeParameterText();
	String blaTextParameterText();
	String datasourceParameterText();
	String headlineParameterText();
	String separatorParameterText();
	String textParameterText();
	String fileSelectionParameterText();
	
	String enableFileUpload();
	String enableTeamSpaceSelection();
	String enableFileServerSelection();
	
	String maximalFileSize();
	String allowedFileExtensions();
	
	String minNumberOfFiles();
	String maxNumberOfFiles();
	String enableDownload();
	String uploadedFile();
	String teamspaceFile();
	String fileserverFile();
	String dropdownFirstValuePerDefaultInfo();
	
	String returnNullWhenBlank();
	
}
