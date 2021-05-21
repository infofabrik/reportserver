package net.datenwerke.rs.oauth.service.oauth;

/**
 * Identifies datasinks that can be authenticated by OAuth2.
 */
public interface OAuthAuthenticatable {

   void setRefreshToken(String refreshToken);

   String getAppKey();

   String getSecretKey();

}
