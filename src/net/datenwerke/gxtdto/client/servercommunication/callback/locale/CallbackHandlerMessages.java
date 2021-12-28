package net.datenwerke.gxtdto.client.servercommunication.callback.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface CallbackHandlerMessages extends Messages {

   public static CallbackHandlerMessages INSTANCE = GWT.create(CallbackHandlerMessages.class);

   String defaultErrorMessage();

   String timeoutMessage();

}
