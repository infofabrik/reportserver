package net.datenwerke.rs.rest.service.rest;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

import net.datenwerke.rs.rest.service.rest.annotations.RestAuthentication;
import net.datenwerke.rs.utils.guice.GuiceMatchers;

public class RestModule extends AbstractModule {

   @Override
   protected void configure() {
      
      RestAuthenticationCheckInterceptor restAuthenticationInterceptor = new RestAuthenticationCheckInterceptor();
      bindInterceptor(Matchers.any(), Matchers.annotatedWith(RestAuthentication.class), restAuthenticationInterceptor);
      bindInterceptor(Matchers.annotatedWith(RestAuthentication.class),
            Matchers.not(Matchers.annotatedWith(RestAuthentication.class)).and(GuiceMatchers.publicMethod()),
            restAuthenticationInterceptor);
      requestInjection(restAuthenticationInterceptor);
   }
   
}
