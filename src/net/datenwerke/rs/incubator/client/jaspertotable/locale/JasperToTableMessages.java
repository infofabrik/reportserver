package net.datenwerke.rs.incubator.client.jaspertotable.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface JasperToTableMessages extends Messages {

   public final static JasperToTableMessages INSTANCE = GWT.create(JasperToTableMessages.class);

   String editJasperToTableBtnText();

   String allowJasperToTableLabel();

   String datasourceLabel();

   String exportDescription();

}
