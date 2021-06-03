package net.datenwerke.rs.onedrive.service.onedrive;

import static net.datenwerke.rs.onedrive.service.onedrive.OneDriveModule.PROPERTY_ONEDRIVE_DISABLED;
import static net.datenwerke.rs.onedrive.service.onedrive.OneDriveModule.PROPERTY_ONEDRIVE_SCHEDULING_ENABLED;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.inject.Provider;

import org.apache.commons.io.IOUtils;

import com.github.scribejava.apis.LiveApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.gwt.safehtml.shared.UriUtils;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.onedrive.service.onedrive.annotations.DefaultOneDriveDatasink;
import net.datenwerke.rs.onedrive.service.onedrive.definitions.OneDriveDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class OneDriveServiceImpl implements OneDriveService {
   private static final String UPLOAD_URL = "https://graph.microsoft.com/v1.0/me/drive/root:";

   private final Provider<ReportService> reportServiceProvider;

   private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

   private final Provider<Optional<OneDriveDatasink>> defaultDatasinkProvider;
   private final Provider<DatasinkService> datasinkServiceProvider;

   @Inject
   public OneDriveServiceImpl(
         Provider<ReportService> reportServiceProvider,
         Provider<DatasinkService> datasinkServiceProvider,
         @DefaultOneDriveDatasink Provider<Optional<OneDriveDatasink>> defaultDatasinkProvider
         ) {
      this.reportServiceProvider = reportServiceProvider;
      this.defaultDatasinkProvider = defaultDatasinkProvider;
      this.datasinkServiceProvider = datasinkServiceProvider;
   }

   @Override
   public void exportIntoOneDrive(Object report, OneDriveDatasink oneDriveDatasink, String filename, String folder)
         throws IOException, InterruptedException, ExecutionException {
      if (!isOneDriveEnabled())
         throw new IllegalStateException("OneDrive is disabled");

      Objects.requireNonNull(oneDriveDatasink, "Datasink is null");

      final String refreshToken = oneDriveDatasink.getRefreshToken();

      Objects.requireNonNull(refreshToken,
            "OneDrive authentication not configured, please setup by using the \"Datasink OAuth2 Authentication Setup\" button.");

      final OAuth20Service oauthService = new ServiceBuilder(oneDriveDatasink.getAppKey()).apiSecret(oneDriveDatasink.getSecretKey())
            .build(oneDriveDatasink.getOAuthApi());

      System.out.println("Refresh token: " + refreshToken);
      OAuth2AccessToken accessToken = oauthService.refreshAccessToken(refreshToken);

      try (InputStream is = reportServiceProvider.get().createInputStream(report)) {
         final OAuthRequest request = new OAuthRequest(Verb.PUT,
               UPLOAD_URL + UriUtils.encode((folder.endsWith("/") ? folder : folder + "/") + filename)
                     + ":/content");
         request.setPayload(IOUtils.toByteArray(is));
         oauthService.signRequest(accessToken, request);

         try (Response response = oauthService.execute(request)) {
            int responseCode = response.getCode();
            if (200 != responseCode && 201 != responseCode)
               throw new IOException("Could not upload to OneDrive. Response code = " + responseCode
                     + ", response body = ['" + response.getBody() + "']");
         }
      }
   }

   @Override
   public Map<StorageType, Boolean> getStorageEnabledConfigs() {
      return datasinkServiceProvider.get().getEnabledConfigs(StorageType.ONEDRIVE, PROPERTY_ONEDRIVE_DISABLED,
            StorageType.ONEDRIVE_SCHEDULING, PROPERTY_ONEDRIVE_SCHEDULING_ENABLED);
   }

   @Override
   public boolean isOneDriveEnabled() {
      return datasinkServiceProvider.get().isEnabled(PROPERTY_ONEDRIVE_DISABLED);
   }

   @Override
   public boolean isOneDriveSchedulingEnabled() {
      return datasinkServiceProvider.get().isSchedulingEnabled(PROPERTY_ONEDRIVE_SCHEDULING_ENABLED);
   }

   @Override
   public void testOneDriveDatasink(OneDriveDatasink oneDriveDatasink)
         throws IOException, InterruptedException, ExecutionException {
      if (!isOneDriveEnabled())
         throw new IllegalStateException("OneDrive datasink is disabled");
      exportIntoOneDrive("ReportServer OneDrive Datasink Test " + dateFormat.format(Calendar.getInstance().getTime()),
            oneDriveDatasink, "reportserver-onedrive-test.txt", oneDriveDatasink.getFolder());

   }

   @Override
   public Optional<OneDriveDatasink> getDefaultDatasink() {
      return defaultDatasinkProvider.get();
   }

}