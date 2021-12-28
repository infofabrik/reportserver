package net.datenwerke.rs.oauth.service.oauth;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.oauth.AuthorizationUrlBuilder;

/**
 * Identifies datasinks that can be authenticated by OAuth2.
 */
public interface OAuthAuthenticatable {

   Long getId();

   void setRefreshToken(String refreshToken);

   String getAppKey();

   String getSecretKey();

   DefaultApi20 getOAuthApi();

   String buildAuthorizationUrl(AuthorizationUrlBuilder authorizationUrlBuilder);

}
