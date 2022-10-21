package net.datenwerke.rs.userprofile.client.userprofile.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface UserProfileMessages extends Messages {

   public final static UserProfileMessages INSTANCE = GWT.create(UserProfileMessages.class);

   String profileHeader(String displayTitle);

   String headerText(String displayTitle);
}
