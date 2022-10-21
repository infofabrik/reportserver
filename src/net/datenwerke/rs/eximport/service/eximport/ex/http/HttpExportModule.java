package net.datenwerke.rs.eximport.service.eximport.ex.http;

import com.google.inject.AbstractModule;
import com.google.inject.servlet.ServletScopes;

public class HttpExportModule extends AbstractModule {

   @Override
   protected void configure() {
      /* bind http config to session */
      bind(HttpExportService.class).to(HttpExportServiceImpl.class).in(ServletScopes.SESSION);

   }

}
