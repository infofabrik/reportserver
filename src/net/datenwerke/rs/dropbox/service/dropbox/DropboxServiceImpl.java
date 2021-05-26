package net.datenwerke.rs.dropbox.service.dropbox;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.inject.Provider;

import org.apache.commons.io.IOUtils;

import com.github.scribejava.apis.DropboxApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.dropbox.service.dropbox.annotations.DefaultDropboxDatasink;
import net.datenwerke.rs.dropbox.service.dropbox.definitions.DropboxDatasink;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.utils.config.ConfigService;

public class DropboxServiceImpl implements DropboxService {

   private static final String PROPERTY_DROPBOX_DISABLED = "dropbox[@disabled]";
   private static final String PROPERTY_DROPBOX_SCHEDULER_ENABLED = "dropbox[@supportsScheduling]";

   private static final String UPLOAD_URL = "https://content.dropboxapi.com/2/files/upload";

   private final Provider<ConfigService> configServiceProvider;
   private final Provider<ReportService> reportServiceProvider;

   private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
   
   private final Provider<Optional<DropboxDatasink>> defaultDatasinkProvider;

   @Inject
   public DropboxServiceImpl(
         Provider<ConfigService> configServiceProvider,
         Provider<ReportService> reportServiceProvider,
         @DefaultDropboxDatasink Provider<Optional<DropboxDatasink>> defaultDatasinkProvider
         ) {
      this.configServiceProvider = configServiceProvider;
      this.reportServiceProvider = reportServiceProvider;
      this.defaultDatasinkProvider = defaultDatasinkProvider;
   }

   @Override
   public void exportIntoDropbox(Object report, DropboxDatasink dropboxDatasink, String filename, String folder)
         throws IOException, InterruptedException, ExecutionException {
      if (!isDropboxEnabled())
         throw new IllegalStateException("Dropbox is disabled");
      
      Objects.requireNonNull(dropboxDatasink, "Datasink is null");

      final String refreshToken = dropboxDatasink.getRefreshToken();

      Objects.requireNonNull(refreshToken,
            "Dropbox authentication not configured, please setup by using the \"Datasink OAuth2 Authentication Setup\" button.");

      final OAuth20Service oauthService = new ServiceBuilder(dropboxDatasink.getAppKey())
            .apiSecret(dropboxDatasink.getSecretKey()).build(DropboxApi.instance());

      OAuth2AccessToken accessToken = oauthService.refreshAccessToken(refreshToken);

      try (InputStream is = reportServiceProvider.get().createInputStream(report)) {
         final OAuthRequest request = new OAuthRequest(Verb.POST, UPLOAD_URL);
         request.addHeader("Content-Type", "application/octet-stream");
         request.addHeader("Dropbox-API-Arg",
               "{\"path\": \"" + (folder.endsWith("/") ? folder : folder + "/") + filename
                     + "\",\"mode\": \"overwrite\",\"autorename\": false,\"mute\": false,\"strict_conflict\": false}");
         request.setPayload(IOUtils.toByteArray(is));
         oauthService.signRequest(accessToken, request);

         try (Response response = oauthService.execute(request)) {
            int responseCode = response.getCode();
            if (200 != responseCode)
               throw new IOException("Could not upload to Dropbox. Response code = " + responseCode
                     + ", response body = ['" + response.getBody() + "']");
         }
      }
   }

   @Override
   public Map<StorageType, Boolean> getStorageEnabledConfigs() {
      Map<StorageType, Boolean> configs = new HashMap<>();
      configs.put(StorageType.DROPBOX, isDropboxEnabled());
      configs.put(StorageType.DROPBOX_SCHEDULING, isDropboxSchedulingEnabled());
      return configs;
   }

   @Override
   public boolean isDropboxEnabled() {
      return !configServiceProvider.get().getConfigFailsafe(DatasinkModule.CONFIG_FILE).getBoolean(PROPERTY_DROPBOX_DISABLED, false);
   }

   @Override
   public boolean isDropboxSchedulingEnabled() {
      return configServiceProvider.get().getConfigFailsafe(DatasinkModule.CONFIG_FILE).getBoolean(PROPERTY_DROPBOX_SCHEDULER_ENABLED,
            true);
   }

   @Override
   public void testDropboxDataSink(DropboxDatasink dropboxDatasink)
         throws IOException, InterruptedException, ExecutionException {
      if (!isDropboxEnabled())
         throw new IllegalStateException("Dropbox datasink is disabled");
      exportIntoDropbox("ReportServer Dropbox Datasink Test " + dateFormat.format(Calendar.getInstance().getTime()),
            dropboxDatasink, "reportserver-dropbox-test.txt", dropboxDatasink.getFolder());

   }

   @Override
   public Optional<DropboxDatasink> getDefaultDatasink() {
      return defaultDatasinkProvider.get();
   }

}
