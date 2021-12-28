package net.datenwerke.gf.client.localization.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface LocalizationMessages extends Messages {

   public static final LocalizationMessages INSTANCE = GWT.create(LocalizationMessages.class);

   String language();

}