package net.datenwerke.rs.oauth.service.oauth;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import javax.inject.Provider;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import net.datenwerke.gf.service.history.HistoryService;
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.entities.AbstractDatasinkManagerNode;
import net.datenwerke.rs.core.service.reportserver.ServerInfoContainer;

public class OAuthAuthenticationServiceImpl implements OAuthAuthenticationService {

   private final Provider<DatasinkService> datasinkServiceProvider;
   private final Provider<ServerInfoContainer> serverInfoProvider;

   @Inject
   public OAuthAuthenticationServiceImpl(
         Provider<DatasinkService> datasinkServiceProvider,
         Provider<HistoryService> historyServiceProvider,
         Provider<ServerInfoContainer> serverInfoProvider
         ) {
      this.datasinkServiceProvider = datasinkServiceProvider;
      this.serverInfoProvider = serverInfoProvider;
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public void generateRefreshToken(String authenticationCode, OAuthAuthenticatable oAuthDatasink,
         String redirectUri) throws IOException, ExecutionException, InterruptedException {
      Objects.requireNonNull(oAuthDatasink, "Datasink is null");
      Objects.requireNonNull(oAuthDatasink, "authentication code is null");

      String appKey = oAuthDatasink.getAppKey();
      String secretKey = oAuthDatasink.getSecretKey();

      if (null == appKey || appKey.trim().contentEquals("") || null == secretKey || secretKey.trim().contentEquals(""))
         throw new IllegalArgumentException("Datasink is not configured correctly");

      final OAuth20Service oauthService = buildOAuthService(oAuthDatasink);

      // get access token and refresh token
      OAuth2AccessToken accessToken = oauthService.getAccessToken(authenticationCode);
      oAuthDatasink.setRefreshToken(accessToken.getRefreshToken());

      datasinkServiceProvider.get().merge((AbstractDatasinkManagerNode) oAuthDatasink);

   }

   @Override
   public String generateAuthenticationUrl(OAuthAuthenticatable oAuthDatasink) {
      final OAuth20Service oauthService = buildOAuthService(oAuthDatasink);
      
      final String authUrl = oAuthDatasink.buildAuthorizationUrl(oauthService
            .createAuthorizationUrlBuilder()
            .state("{\"datasinkId\":" + oAuthDatasink.getId() + "}")
         );
      
      return authUrl;
   }
   
   private OAuth20Service buildOAuthService(OAuthAuthenticatable oAuthDatasink) {
      String appKey = oAuthDatasink.getAppKey();
      String secretKey = oAuthDatasink.getSecretKey();
      
      return new ServiceBuilder(appKey)
            .apiSecret(secretKey)
            .callback(getRedirectUri())
            .build(oAuthDatasink.getOAuthApi());
   }

   @Override
   public String getRedirectUri() {
      ServerInfoContainer serverInfo = serverInfoProvider.get();
      return serverInfo.getBaseURL() + "/oauth";
   }

}