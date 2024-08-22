package net.datenwerke.rs.googledrive.service.googledrive.definitions;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.codec.binary.Hex;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.oauth.AuthorizationUrlBuilder;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.dtoservices.dtogenerator.annotations.AdditionalField;
import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.eximport.ex.annotations.ExportableField;
import net.datenwerke.gf.base.service.annotations.Field;
import net.datenwerke.gf.base.service.annotations.Indexed;
import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.FolderedDatasink;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkFilenameFolderConfig;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.googledrive.service.googledrive.GoogleDriveService;
import net.datenwerke.rs.googledrive.service.googledrive.definitions.dtogen.GoogleDriveDatasink2DtoPostProcessor;
import net.datenwerke.rs.googledrive.service.googledrive.locale.GoogleDriveDatasinkMessages;
import net.datenwerke.rs.oauth.service.oauth.OAuthAuthenticatable;
import net.datenwerke.rs.utils.entitymerge.service.annotations.EntityMergeField;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;
import net.datenwerke.rs.utils.misc.DateUtils;
import net.datenwerke.security.service.crypto.pbe.PbeService;
import net.datenwerke.security.service.crypto.pbe.encrypt.EncryptionService;

/**
 * Used to define GoogleDrive datasinks that can be used in ReportServer to send
 * reports to a given google drive account.
 */
@Entity
@Table(name = "GOOGLEDRIVE_DATASINK")
@Audited
@GenerateDto(
      dtoPackage = "net.datenwerke.rs.googledrive.client.googledrive.dto", 
      poso2DtoPostProcessors = GoogleDriveDatasink2DtoPostProcessor.class, 
      additionalFields = {
            @AdditionalField(
                  name = "hasRefreshToken", 
                  type = Boolean.class
                  ),
            @AdditionalField(
                  name = "hasSecretKey", 
                  type = Boolean.class
                  ) 
            }, 
            icon = "google_drive"
            )
@InstanceDescription(
      msgLocation = GoogleDriveDatasinkMessages.class, 
      objNameKey = "googleDriveDatasinkTypeName", 
      icon = "google_drive"
      )
@Indexed
public class GoogleDriveDatasink extends DatasinkDefinition implements OAuthAuthenticatable, FolderedDatasink {

   /**
    * 
    */
   private static final long serialVersionUID = -5340502195887784259L;

   @Inject
   protected static Provider<PbeService> pbeServiceProvider;
   
   @Inject
   protected static Provider<GoogleDriveService> basicDatasinkService;

   @ExposeToClient
   @Field
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   @EntityMergeField
   private String appKey;

   @ExposeToClient(exposeValueToClient = false, mergeDtoValueBack = true)
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   @EntityMergeField
   @ExportableField(exportField = false)
   private String secretKey;

   @ExposeToClient(exposeValueToClient = false, mergeDtoValueBack = true)
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   @EntityMergeField
   private String refreshToken;

   @ExposeToClient
   @Field
   @Column(length = 1024)
   @EntityMergeField
   private String folder = "/";

   @Override
   public String getFolder() {
      return folder;
   }

   public void setFolder(String folder) {
      this.folder = folder;
   }

   /**
    * Gets the decrypted refresh token.
    * 
    * @return the decrypted refresh token
    */
   public String getRefreshToken() {
      if (null == refreshToken)
         return null;
      if ("".equals(refreshToken))
         return "";

      EncryptionService encryptionService = pbeServiceProvider.get().getEncryptionService();
      return new String(encryptionService.decryptFromHex(refreshToken));
   }

   /**
    * Encrypts and sets the given refresh token.
    * 
    * @param refreshToken the refresh token to encrypt and set
    */
   @Override
   public void setRefreshToken(String refreshToken) {
      if (null == refreshToken)
         refreshToken = "";

      EncryptionService encryptionService = pbeServiceProvider.get().getEncryptionService();
      byte[] encrypted = encryptionService.encrypt(refreshToken);

      this.refreshToken = new String(Hex.encodeHex(encrypted));
   }

   @Override
   public String getAppKey() {
      return appKey;
   }

   public void setAppKey(String appKey) {
      this.appKey = appKey;
   }

   /**
    * Gets the decrypted secret key
    * 
    * @return the decrypted secret key
    */
   @Override
   public String getSecretKey() {
      if (null == secretKey)
         return null;
      if ("".equals(secretKey))
         return "";

      EncryptionService encryptionService = pbeServiceProvider.get().getEncryptionService();
      return new String(encryptionService.decryptFromHex(secretKey));
   }

   /**
    * Encrypts and sets the given secret key.
    * 
    * @param secretKey the secret key to encrypt and set
    */

   public void setSecretKey(String secretKey) {
      if (null == secretKey) {
         this.secretKey = null;
         return;
      }

      EncryptionService encryptionService = pbeServiceProvider.get().getEncryptionService();
      byte[] encrypted = encryptionService.encrypt(secretKey);

      this.secretKey = new String(Hex.encodeHex(encrypted));
   }

   @Override
   public DefaultApi20 getOAuthApi() {
      return GoogleApi20.instance();
   }

   @Override
   public String buildAuthorizationUrl(AuthorizationUrlBuilder authorizationUrlBuilder) {
      Map<String, String> additionalParameters = new HashMap<>();
      additionalParameters.put("access_type", "offline");
      additionalParameters.put("prompt", "consent");
      return authorizationUrlBuilder.scope("https://www.googleapis.com/auth/drive.file")
            .additionalParams(additionalParameters).build();
   }

   @Override
   public BasicDatasinkService getDatasinkService() {
      return basicDatasinkService.get();
   }

   @Override
   public DatasinkConfiguration getDefaultConfiguration(String fileEnding) {
      return new DatasinkFilenameFolderConfig() {

         @Override
         public String getFolder() {
            return folder;
         }

         @Override
         public String getFilename() {
            return DEFAULT_EXPORT_FILENAME + DateUtils.formatCurrentDate() + fileEnding;
         }
      };
   }

}
