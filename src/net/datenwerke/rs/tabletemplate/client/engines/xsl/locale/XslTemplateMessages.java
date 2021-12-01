package net.datenwerke.rs.tabletemplate.client.engines.xsl.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface XslTemplateMessages extends Messages {

	public static XslTemplateMessages INSTANCE = GWT.create(XslTemplateMessages.class);
	
	String templateTypeDescription();

}
