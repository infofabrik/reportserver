package net.datenwerke.gf.client.managerhelper.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface ManagerhelperMessages extends Messages {

   public final static ManagerhelperMessages INSTANCE = GWT.create(ManagerhelperMessages.class);

   String mainPerspective();

   String tree();

   String updating();

   String updated();

   String upoadError();

}
