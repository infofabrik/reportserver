package net.datenwerke.rs.core.service.genrights.access;

import com.google.inject.Inject;

import net.datenwerke.security.service.security.SecurityService;

public class GenRightsAccessRsStartup {

   @Inject
   public GenRightsAccessRsStartup(SecurityService securityService) {

      /* register security targets */
      securityService.registerSecurityTarget(AccessRsSecurityTarget.class);
   }
}
