package net.datenwerke.rs.rest.service.rest;

import javax.inject.Singleton;

import org.glassfish.hk2.api.InterceptionService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class RestInterceptorBinder extends AbstractBinder {
   
   @Override
   protected void configure()
   {
      bind(RestInterceptorService.class).to(InterceptionService.class).in(Singleton.class);
   }
}
