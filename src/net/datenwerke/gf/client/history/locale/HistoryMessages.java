package net.datenwerke.gf.client.history.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface HistoryMessages extends Messages {

   public final HistoryMessages INSTANCE = GWT.create(HistoryMessages.class);

   String jumpTo();

}
