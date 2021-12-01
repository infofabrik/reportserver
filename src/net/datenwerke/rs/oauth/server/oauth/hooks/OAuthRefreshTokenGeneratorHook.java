package net.datenwerke.rs.oauth.server.oauth.hooks;

import com.github.scribejava.core.oauth.OAuth20Service;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.oauth.service.oauth.OAuthAuthenticatable;

public interface OAuthRefreshTokenGeneratorHook extends Hook {

   boolean consumes(OAuthAuthenticatable oAuthDatasink);

   String generateRefreshToken(OAuth20Service oauthService, OAuthAuthenticatable oAuthDatasink,
         String authenticationCode) throws Exception;

}
