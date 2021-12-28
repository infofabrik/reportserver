package net.datenwerke.rs.fileserver.client.fileserver.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface FileServerMessages extends Messages {

	public static final FileServerMessages INSTANCE = GWT.create(FileServerMessages.class);
	
	String adminLabel();

	String mainPanelHeading();

	String folder();
	String insert();

	String permissionModuleDescription();

	String file();

	String editFolder();
	String editFile();
	
	String contentTypeLabel();
	String fileUploadLabel();
	String previewLabel();

	String sizeLabel();
	String sizeValue(int bytes);
	String sizeValueKb(double d);
	String sizeValueMb(double d);

	String publiclyAccessibleLabel();

	String previewUrlLabel();

	String fileViewHeader();

	String importConfigFailureNoParent();

	String importWhereTo();

	String importMainPropertiesDescription();

	String importMainPropertiesHeadline();

	String selectFileFromFileServerText();

	String folderDescription();
	String fileDescription();

	String zipUploadedTitle();
	String zipUploadedMsg();
	
	String fileCompress();



	



}
