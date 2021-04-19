package net.datenwerke.rs.jxlsreport.client.jxlsreport.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface JxlsReportMessages extends Messages{

	public final static JxlsReportMessages INSTANCE = GWT.create(JxlsReportMessages.class);
	
	String reportTypeName();

	String editReport();

	String templateUpload();

	String fileName();

	String useLegacyJxls();

}
