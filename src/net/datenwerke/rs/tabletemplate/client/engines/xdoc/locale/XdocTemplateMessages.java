package net.datenwerke.rs.tabletemplate.client.engines.xdoc.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface XdocTemplateMessages extends Messages {
	
	public static XdocTemplateMessages INSTANCE = GWT.create(XdocTemplateMessages.class);
	
	String templateTypeDescription();

}
