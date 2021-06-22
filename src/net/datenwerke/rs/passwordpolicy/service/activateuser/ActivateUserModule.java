package net.datenwerke.rs.passwordpolicy.service.activateuser;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;

public class ActivateUserModule extends AbstractReportServerModule {

   public static final String CONFIG_FILE = "security/activateuser.cf"; //$NON-NLS-1$
   public static final String PROPERTY_EMAIL_SUBJECT = "security.activateaccount.email.subject";
   public static final String PROPERTY_EMAIL_TEXT = "security.activateaccount.email.text";

   @Override
   protected void configure() {
      /* bind service */
      bind(ActivateUserService.class).to(ActivateUserServiceImpl.class);
   }

}
