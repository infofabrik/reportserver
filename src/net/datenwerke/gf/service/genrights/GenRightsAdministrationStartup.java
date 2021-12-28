package net.datenwerke.gf.service.genrights;

import com.google.inject.Inject;

import net.datenwerke.security.service.security.SecurityService;

public class GenRightsAdministrationStartup {

   @Inject
   public GenRightsAdministrationStartup(SecurityService securityService) {

      /* register security targets */
      securityService.registerSecurityTarget(AdministrationViewSecurityTarget.class);
   }
}
