package net.datenwerke.rs.transport.service.transport;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class TransportModule extends AbstractModule {
   
   public static final int SHORT_KEY_LENGTH = 8;

   @Override
   protected void configure() {
      bind(TransportService.class).to(TransportServiceImpl.class);
      
      bind(TransportTreeService.class).to(TransportTreeServiceImpl.class).in(Scopes.SINGLETON);
      
      bind(TransportStartup.class).asEagerSingleton();
   }

}
