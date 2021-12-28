package net.datenwerke.rs.incubator.service.notify;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;

public class NotifyModule extends AbstractReportServerModule {

   @Override
   protected void configure() {
      bind(NotifyStartup.class).asEagerSingleton();
   }

}
