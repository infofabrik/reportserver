package net.datenwerke.security.service.genrights.security;

import com.google.inject.Inject;

import net.datenwerke.security.service.security.SecurityService;

public class GenRightsSecurityStartup {

   @Inject
   public GenRightsSecurityStartup(SecurityService securityService) {

      /* register security targets */
      securityService.registerSecurityTarget(GenericSecurityTargetAdminViewSecurityTarget.class);
   }
}
