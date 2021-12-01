package net.datenwerke.rs.tabletemplate.client.engines.jxls2.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface JXls2TemplateMessages extends Messages {
	
	public static JXls2TemplateMessages INSTANCE = GWT.create(JXls2TemplateMessages.class);
	
	String templateTypeDescription();

}
