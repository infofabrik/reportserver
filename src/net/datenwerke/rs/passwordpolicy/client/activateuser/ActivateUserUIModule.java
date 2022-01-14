package net.datenwerke.rs.passwordpolicy.client.activateuser;

import com.google.gwt.inject.client.AbstractGinModule;

public class ActivateUserUIModule extends AbstractGinModule {

   public static final String PROPERTY_CONTAINER = "Security_ActivateUser_PropertyContainer"; //$NON-NLS-1$
   public static final String PROPERTY_EMAIL_SUBJECT = "security:activateaccount:email:subject";
   public static final String PROPERTY_EMAIL_TEXT = "security:activateaccount:email:text";

   @Override
   protected void configure() {
      bind(ActivateUserUIStartup.class).asEagerSingleton();
   }

}
