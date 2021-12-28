package net.datenwerke.gf.client.administration.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface AdministrationMessages extends Messages {

   public static AdministrationMessages INSTANCE = GWT.create(AdministrationMessages.class);

   String administrationHeader();

   String administration();

   String adminSecurityTargetDescription();

}
