package net.datenwerke.rs.oauth.service.oauth;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class DummyOAuthenticationServiceImpl implements OAuthAuthenticationService {

   @Override
   public void generateRefreshToken(String authenticationCode, OAuthAuthenticatable oAuthDatasink, String redirectUri)
         throws IOException, InterruptedException, ExecutionException {
   }

   @Override
   public String generateAuthenticationUrl(OAuthAuthenticatable oAuthDatasink) {
      return null;
   }

   @Override
   public String getRedirectUri() {
      return null;
   }

}
