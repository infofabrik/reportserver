package net.datenwerke.rs.base.client.reportengines.jasper.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface JasperMessages extends Messages {

	public final static JasperMessages INSTANCE = GWT.create(JasperMessages.class);
	
	public String reportTypeName();

	public String jasperDownloadToolbarButtonText();

	public String fileMustBeJrxml();
}
