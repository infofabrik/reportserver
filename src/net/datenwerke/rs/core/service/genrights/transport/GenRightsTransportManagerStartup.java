package net.datenwerke.rs.core.service.genrights.transport;

import com.google.inject.Inject;

import net.datenwerke.security.service.security.SecurityService;

public class GenRightsTransportManagerStartup {

   @Inject
   public GenRightsTransportManagerStartup(SecurityService securityService) {

      /* register security targets */
      securityService.registerSecurityTarget(TransportManagerAdminViewSecurityTarget.class);
   }
}
