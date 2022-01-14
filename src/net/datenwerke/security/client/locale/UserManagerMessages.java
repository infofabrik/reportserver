package net.datenwerke.security.client.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface UserManagerMessages extends Messages {

   public final static UserManagerMessages INSTANCE = GWT.create(UserManagerMessages.class);

   String user();

   String group();

   String ou();

}
