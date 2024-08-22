package net.datenwerke.rs.keyutils.service.keyutils;

import com.google.inject.AbstractModule;

public class KeyUtilsModule extends AbstractModule {

   @Override
   protected void configure() {
      /* bind service */
      bind(KeyNameGeneratorService.class).to(KeyNameGeneratorServiceImpl.class);
      bind(KeyMatchService.class).to(KeyMatchServiceImpl.class);
   }

}
