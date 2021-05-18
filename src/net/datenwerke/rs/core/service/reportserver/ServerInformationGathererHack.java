package net.datenwerke.rs.core.service.reportserver;

import javax.servlet.http.HttpServletRequest;

import net.datenwerke.security.service.authenticator.AuthenticationResult;
import net.datenwerke.security.service.authenticator.hooks.PostAuthenticateHook;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ServerInformationGathererHack implements PostAuthenticateHook {

   private Provider<HttpServletRequest> requestProvider;
   private Provider<ReportServerService> serviceProvider;

   @Inject
   public ServerInformationGathererHack(Provider<HttpServletRequest> requestProvider,
         Provider<ReportServerService> serviceProvider) {
      this.requestProvider = requestProvider;
      this.serviceProvider = serviceProvider;
   }

   @Override
   public void authenticated(AuthenticationResult authRes) {
      if (authRes.isAllowed()) {
         HttpServletRequest httpServletRequest = requestProvider.get();
         if (null == httpServletRequest)
            return;

         ((ReportServerServiceImpl) serviceProvider.get()).init(httpServletRequest);
      }
   }

}
