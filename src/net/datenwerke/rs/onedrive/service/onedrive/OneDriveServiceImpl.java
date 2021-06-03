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
import org.json.JSONException;
import org.json.JSONObject;

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

      OAuth2AccessToken accessToken = oauthService.refreshAccessToken(refreshToken);

      try (InputStream is = reportServiceProvider.get().createInputStream(report)) {
         byte[] reportAsBytes = IOUtils.toByteArray(is);
         int length = reportAsBytes.length;
         // max = 4MB
         if (length >= 4 * 1024 * 1024) 
            uploadLargeFile(folder, filename, reportAsBytes, accessToken, oauthService);
         else 
            uploadSmallFile(folder, filename, reportAsBytes, accessToken, oauthService);
         
      }
   }
   
   private void uploadSmallFile(String folder, String filename, byte[] reportAsBytes, OAuth2AccessToken accessToken,
         OAuth20Service oauthService) throws IOException, InterruptedException, ExecutionException {
      final OAuthRequest request = new OAuthRequest(Verb.PUT,
            UPLOAD_URL + UriUtils.encode((folder.endsWith("/") ? folder : folder + "/") + filename) + ":/content");
      request.setPayload(reportAsBytes);
      oauthService.signRequest(accessToken, request);

      try (Response response = oauthService.execute(request)) {
         int responseCode = response.getCode();
         if (200 != responseCode && 201 != responseCode)
            throw new IOException("Could not upload to OneDrive. Response code = " + responseCode
                  + ", response body = ['" + response.getBody() + "']");
      }
   }
   
   private void uploadLargeFile(String folder, String filename, byte[] reportAsBytes, OAuth2AccessToken accessToken,
         OAuth20Service oauthService) throws IOException, InterruptedException, ExecutionException {
      // we first create an upload session and get the uploadUrl
      OAuthRequest request = new OAuthRequest(Verb.PUT, UPLOAD_URL
            + UriUtils.encode((folder.endsWith("/") ? folder : folder + "/") + filename) + ":/createUploadSession");
      request.setPayload(
            "\"item\": {\"@odata.type\": \"microsoft.graph.driveItemUploadableProperties\", " +
            "\"@microsoft.graph.conflictBehavior\": \"replace\", " + 
            "\"name\": \"testUpload.txt\"" +
            "}");
      oauthService.signRequest(accessToken, request);
      
      String uploadUrl = null;
      
      try (Response response = oauthService.execute(request)) {
         int responseCode = response.getCode();
         if (200 != responseCode && 201 != responseCode)
            throw new IOException("Could not upload to OneDrive. Response code = " + responseCode
                  + ", response body = ['" + response.getBody() + "']");
         String uploadUrlResponse = response.getBody();
         try {
            JSONObject jsonObject = new JSONObject(uploadUrlResponse);
            uploadUrl = jsonObject.getString("uploadUrl");

         } catch (JSONException e) {
            throw new IOException("Error while reading json parameter uploadUrl", e);
         }
      }
      
      Objects.requireNonNull(uploadUrl, "Upload url could not be read");

      // we now upload the file
      int uploadFileSize = reportAsBytes.length;
      request = new OAuthRequest(Verb.PUT, uploadUrl);
      request.addHeader("Content-Length", uploadFileSize + "");
      request.addHeader("Content-Range", "bytes 0-" + (uploadFileSize - 1) + "/" + uploadFileSize);
      request.setPayload(reportAsBytes);
      oauthService.signRequest(accessToken, request);
      try (Response response = oauthService.execute(request)) {
         int responseCode = response.getCode();
         if (200 != responseCode && 201 != responseCode)
            throw new IOException("Could not upload to OneDrive. Response code = " + responseCode
                  + ", response body = ['" + response.getBody() + "']");
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