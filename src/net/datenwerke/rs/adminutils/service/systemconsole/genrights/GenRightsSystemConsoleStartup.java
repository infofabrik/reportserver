package net.datenwerke.rs.adminutils.service.systemconsole.genrights;

import com.google.inject.Inject;

import net.datenwerke.security.service.security.SecurityService;

public class GenRightsSystemConsoleStartup {

   @Inject
   public GenRightsSystemConsoleStartup(SecurityService securityService) {

      /* register security targets */
      securityService.registerSecurityTarget(SystemConsoleSecurityTarget.class);
   }
}
