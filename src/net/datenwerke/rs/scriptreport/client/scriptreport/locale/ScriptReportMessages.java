package net.datenwerke.rs.scriptreport.client.scriptreport.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface ScriptReportMessages extends Messages {

   public final static ScriptReportMessages INSTANCE = GWT.create(ScriptReportMessages.class);

   String reportTypeName();

   String editReport();

   String script();

   String exportFormats();

   String arguments();

   String scriptParameterName();

   String scriptParameterText();

   String parameterDefaultValue();

}
