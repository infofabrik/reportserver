package net.datenwerke.security.service.genrights.usermanager;

import com.google.inject.Inject;

import net.datenwerke.security.service.security.SecurityService;

public class GenRightsUserManagerStartup {

   @Inject
   public GenRightsUserManagerStartup(SecurityService securityService) {

      /* register security targets */
      securityService.registerSecurityTarget(UserManagerAdminViewSecurityTarget.class);
   }
}
