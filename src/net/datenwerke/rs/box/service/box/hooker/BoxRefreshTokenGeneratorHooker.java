package net.datenwerke.rs.box.service.box.hooker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

import net.datenwerke.rs.box.service.box.definitions.BoxDatasink;
import net.datenwerke.rs.oauth.server.oauth.hooks.OAuthRefreshTokenGeneratorHook;
import net.datenwerke.rs.oauth.service.oauth.OAuthAuthenticatable;

public class BoxRefreshTokenGeneratorHooker implements OAuthRefreshTokenGeneratorHook {

   private static final String TOKEN_URL = "https://api.box.com/oauth2/token";

   @Override
   public boolean consumes(OAuthAuthenticatable oAuthDatasink) {
      return oAuthDatasink instanceof BoxDatasink;
   }

   @Override
   public String generateRefreshToken(OAuth20Service oauthService, OAuthAuthenticatable oAuthDatasink,
         String authenticationCode) throws Exception {
      String refreshToken;
      OAuthRequest request = new OAuthRequest(Verb.POST, TOKEN_URL);
      request.addBodyParameter("client_id", oAuthDatasink.getAppKey());
      request.addBodyParameter("client_secret", oAuthDatasink.getSecretKey());
      request.addBodyParameter("code", authenticationCode);
      request.addBodyParameter("grant_type", "authorization_code");
      try (Response response = oauthService.execute(request)) {
         final ObjectNode jsonObject = new ObjectMapper().readValue(response.getBody(), ObjectNode.class);
         final String refreshTokenField = "refresh_token";
         if (jsonObject.has(refreshTokenField))
            refreshToken = jsonObject.get(refreshTokenField).asText();
         else
            throw new IllegalArgumentException("No refresh token found");
      }
      return refreshToken;
   }

}
