package net.datenwerke.rs.rest.service.rest;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

import net.datenwerke.rs.rest.resources.GeneralInfoResource;
import net.datenwerke.rs.rest.service.rest.annotations.RestAuthentication;

public class RestModule extends AbstractModule {

   @Override
   protected void configure() {
      
      bind(GeneralInfoResource.class);
      
      RestAuthenticationCheckInterceptor restAuthenticationInterceptor = new RestAuthenticationCheckInterceptor();
      bindInterceptor(Matchers.any(), Matchers.annotatedWith(RestAuthentication.class), restAuthenticationInterceptor);
      requestInjection(restAuthenticationInterceptor);
   }

}
