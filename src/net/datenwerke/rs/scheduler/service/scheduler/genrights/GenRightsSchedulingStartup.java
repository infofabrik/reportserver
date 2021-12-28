package net.datenwerke.rs.scheduler.service.scheduler.genrights;

import com.google.inject.Inject;

import net.datenwerke.security.service.security.SecurityService;

public class GenRightsSchedulingStartup {

   @Inject
   public GenRightsSchedulingStartup(SecurityService securityService) {

      /* register security targets */
      securityService.registerSecurityTarget(SchedulingAdminSecurityTarget.class);
      securityService.registerSecurityTarget(SchedulingBasicSecurityTarget.class);
   }
}
