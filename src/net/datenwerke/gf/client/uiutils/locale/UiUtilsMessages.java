package net.datenwerke.gf.client.uiutils.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface UiUtilsMessages extends Messages {

   public final UiUtilsMessages INSTANCE = GWT.create(UiUtilsMessages.class);

   String cannotParseDateFormula();

   String dateComputedBy(String formula);

}
