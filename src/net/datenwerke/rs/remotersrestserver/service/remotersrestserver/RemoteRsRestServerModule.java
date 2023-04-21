package net.datenwerke.rs.remotersrestserver.service.remotersrestserver;

import com.google.inject.AbstractModule;

import net.datenwerke.rs.remotersrestserver.service.remotersrestserver.entities.RemoteRsRestServer;

public class RemoteRsRestServerModule extends AbstractModule {

   public final static String TEST_RS_VERSION = "RS_VERSION";
   public final static String TEST_RS_MESSAGE = "RS_MESSAGE";
   public final static String TEST_RS_DATE = "RS_DATE";
   public final static String TEST_RS_MESSAGE_CONTENT = "ReportServer REST Test OK";
   
   @Override
   protected void configure() {
      
      requestStaticInjection(RemoteRsRestServer.class);
      
      bind(RemoteRsRestServerStartup.class).asEagerSingleton();
   }

}
