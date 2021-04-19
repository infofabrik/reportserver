package net.datenwerke.rs.scp.service.scp;

import com.google.inject.AbstractModule;

import net.datenwerke.rs.scp.service.scp.definitions.ScpDatasink;

public class ScpModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(ScpService.class).to(ScpServiceImpl.class);
      requestStaticInjection(ScpDatasink.class);

      bind(ScpStartup.class).asEagerSingleton();
   }

}
