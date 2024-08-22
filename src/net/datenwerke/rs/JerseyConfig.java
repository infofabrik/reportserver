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
            "net.datenwerke.rs.rest",
            "net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.rest",
            "net.datenwerke.rs.base.ext.service.rest.resources",
            "net.datenwerke.rs.dot.service.dot.rest.resources",
            "net.datenwerke.rs.markdown.service.markdown.rest.resources"
            );

      GuiceBridge.getGuiceBridge().initializeGuiceBridge(locator);
      GuiceIntoHK2Bridge guiceBridge = locator.getService(GuiceIntoHK2Bridge.class);
      guiceBridge.bridgeGuiceInjector(ReportServerServiceConfig.injector);
   }
}