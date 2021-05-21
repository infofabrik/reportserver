package net.datenwerke.rs.oauth.service.oauth;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import javax.inject.Provider;

import com.github.scribejava.apis.DropboxApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.entities.AbstractDatasinkManagerNode;

public class OAuthAuthenticationServiceImpl implements OAuthAuthenticationService {

   private final Provider<DatasinkService> datasinkServiceProvider;

   @Inject
   public OAuthAuthenticationServiceImpl(Provider<DatasinkService> datasinkServiceProvider) {
      this.datasinkServiceProvider = datasinkServiceProvider;
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

      final OAuth20Service oauthService = new ServiceBuilder(appKey).apiSecret(secretKey).callback(redirectUri)
            .build(DropboxApi.instance());

      // get access token and refresh token
      OAuth2AccessToken accessToken = oauthService.getAccessToken(authenticationCode);
      oAuthDatasink.setRefreshToken(accessToken.getRefreshToken());

      datasinkServiceProvider.get().merge((AbstractDatasinkManagerNode) oAuthDatasink);

   }

}