package net.datenwerke.rs.dot.service.dot;

import com.google.inject.AbstractModule;

public class DotModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(DotService.class).to(DotServiceImpl.class);
   }

}
