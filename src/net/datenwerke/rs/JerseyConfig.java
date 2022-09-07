package net.datenwerke.rs;

import javax.inject.Inject;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import net.datenwerke.rs.rest.service.rest.RestInterceptorBinder;

public class JerseyConfig extends ResourceConfig {

   @Inject
   public JerseyConfig(ServiceLocator locator) {
      register(new RestInterceptorBinder());
      packages(
            "net.datenwerke.rs.saiku.server.rest", 
            "net.datenwerke.rs.legacysaiku.server.rest",
            "net.datenwerke.rs.rest");

      GuiceBridge.getGuiceBridge().initializeGuiceBridge(locator);
      GuiceIntoHK2Bridge guiceBridge = locator.getService(GuiceIntoHK2Bridge.class);
      guiceBridge.bridgeGuiceInjector(ReportServerServiceConfig.injector);
   }
}