package net.datenwerke.rs.globalconstants.client.globalconstants.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface GlobalConstantsMessages extends Messages {

   public final GlobalConstantsMessages INSTANCE = GWT.create(GlobalConstantsMessages.class);

   public String viewNavigationTitle();

   public String dialogTitle();

   public String dialogDescription();

   public String securityTitle();

   public String securityDescription();

   public String propertyName();

   public String propertyValue();

   public String addConstantText();

   public String confirmRemoveAllTitle();

   public String confirmRemoveAllText();

   public String constantSuccessfullyUpdated();

}
