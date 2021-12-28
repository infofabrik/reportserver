package net.datenwerke.rs.core.service.contexthelp;

import com.google.inject.AbstractModule;

public class ContextHelpModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(ContextHelpService.class).to(ContextHelpServiceImpl.class);
   }

}
