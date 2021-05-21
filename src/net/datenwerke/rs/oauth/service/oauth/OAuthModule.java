package net.datenwerke.rs.oauth.service.oauth;

import com.google.inject.AbstractModule;

public class OAuthModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(OAuthAuthenticationService.class).to(OAuthAuthenticationServiceImpl.class);
   }

}