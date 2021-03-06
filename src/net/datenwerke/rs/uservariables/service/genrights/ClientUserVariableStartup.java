package net.datenwerke.rs.uservariables.service.genrights;

import com.google.inject.Inject;

import net.datenwerke.security.service.security.SecurityService;

public class ClientUserVariableStartup {

   @Inject
   public ClientUserVariableStartup(SecurityService securityService) {

      /* register security targets */
      securityService.registerSecurityTarget(UserVariableAdminViewSecurityTarget.class);
   }
}
