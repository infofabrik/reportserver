package net.datenwerke.rs.passwordpolicy.service.lostpassword;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;

public class LostPasswordModule extends AbstractReportServerModule {

   public static final String CONFIG_FILE = "security/notifications.cf";

   public final static String USER_PROPERTY_TMP_PASSWORD = "lostpassword:tmppassword";
   public final static String USER_PROPERTY_TMP_PASSWORD_DATE = "lostpassword:passworddate";

   @Override
   protected void configure() {
      bind(LostPasswordService.class).to(LostPasswordServiceImpl.class);

      bind(LostPasswordModuleStartup.class).asEagerSingleton();
   }
}
