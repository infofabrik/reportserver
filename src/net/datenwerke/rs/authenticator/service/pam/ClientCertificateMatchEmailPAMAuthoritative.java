package net.datenwerke.rs.authenticator.service.pam;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import com.google.inject.Provider;

import net.datenwerke.rs.utils.properties.ApplicationPropertiesService;
import net.datenwerke.security.service.usermanager.UserManagerService;

public class ClientCertificateMatchEmailPAMAuthoritative extends ClientCertificateMatchEmailPAM {

   @Inject
   public ClientCertificateMatchEmailPAMAuthoritative(Provider<HttpServletRequest> requestProvider,
         ApplicationPropertiesService propsService, UserManagerService userManagerService) {

      super(requestProvider, propsService, userManagerService);
   }

   @Override
   protected boolean isAuthoritative() {
      return true;
   }
}
