package net.datenwerke.rs.transport.service.transport;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

import net.datenwerke.rs.transport.service.transport.entities.Transport;
import net.datenwerke.rs.transport.service.transport.vfs.TransportManagerVFSModule;

public class TransportModule extends AbstractModule {
   
   public static final int SHORT_KEY_LENGTH = 8;

   @Override
   protected void configure() {
      install(new TransportManagerVFSModule());

      /* bind services */
      bind(TransportService.class).to(TransportServiceImpl.class);
      bind(TransportTreeService.class).to(TransportTreeServiceImpl.class).in(Scopes.SINGLETON);
      
      /* static injection */
      requestStaticInjection(Transport.class);

      /* startup */
      bind(TransportStartup.class).asEagerSingleton();
   }

}
