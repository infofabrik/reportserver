package net.datenwerke.rs.base.ext.client.parameters.fileselection.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface FileSelectionParameterMessages extends Messages {

	public final static FileSelectionParameterMessages INSTANCE = GWT.create(FileSelectionParameterMessages.class);

	String maxFileSizeExceeded(String fileSizeString);

	String mismatchingFileExtension(String extensions);
	
	
}
