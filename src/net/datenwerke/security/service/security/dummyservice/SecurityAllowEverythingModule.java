package net.datenwerke.security.service.security.dummyservice;

import com.google.inject.AbstractModule;

import net.datenwerke.security.service.security.SecurityService;

public class SecurityAllowEverythingModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(SecurityService.class).to(SecurityAllowEverythingService.class);
   }

}
