package net.datenwerke.rs.core.service.genrights.remoteservers;

import com.google.inject.Inject;

import net.datenwerke.security.service.security.SecurityService;

public class GenRightsRemoteServerManagerStartup {

   @Inject
   public GenRightsRemoteServerManagerStartup(SecurityService securityService) {

      /* register security targets */
      securityService.registerSecurityTarget(RemoteServerManagerAdminViewSecurityTarget.class);
   }
}
