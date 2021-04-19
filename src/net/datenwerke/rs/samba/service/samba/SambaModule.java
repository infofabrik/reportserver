package net.datenwerke.rs.samba.service.samba;

import com.google.inject.AbstractModule;

import net.datenwerke.rs.samba.service.samba.definitions.SambaDatasink;

public class SambaModule extends AbstractModule {

   @Override
   protected void configure() {
      requestStaticInjection(SambaDatasink.class);

      bind(SambaStartup.class).asEagerSingleton();
   }

}