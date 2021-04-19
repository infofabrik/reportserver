package net.datenwerke.rs.tabletemplate.client.engines.jxls.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface JXlsTemplateMessages extends Messages {
	
	public static JXlsTemplateMessages INSTANCE = GWT.create(JXlsTemplateMessages.class);
	
	String templateTypeDescription();

}
