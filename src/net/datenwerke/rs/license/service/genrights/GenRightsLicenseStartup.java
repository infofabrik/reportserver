package net.datenwerke.rs.license.service.genrights;

import com.google.inject.Inject;

import net.datenwerke.security.service.security.SecurityService;

public class GenRightsLicenseStartup {

   @Inject
   public GenRightsLicenseStartup(SecurityService securityService) {

      /* register security targets */
      securityService.registerSecurityTarget(LicenseSecurityTarget.class);
   }
}
