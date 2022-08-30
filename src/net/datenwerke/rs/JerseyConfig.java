package net.datenwerke.rs;

import javax.inject.Inject;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

public class JerseyConfig extends ResourceConfig {

   @Inject
   public JerseyConfig(ServiceLocator locator) {
       packages("net.datenwerke.rs.saiku.server.rest", "net.datenwerke.rs.legacysaiku.server.rest");

       GuiceBridge.getGuiceBridge().initializeGuiceBridge(locator);
       // add your Guice modules.
//       Injector injector = Guice.createInjector(new GuiceModule());
       GuiceIntoHK2Bridge guiceBridge = locator.getService(GuiceIntoHK2Bridge.class);
       guiceBridge.bridgeGuiceInjector(ReportServerServiceConfig.injector);
       
   }
}