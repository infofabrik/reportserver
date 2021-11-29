package net.datenwerke.rs.onedrive.service.onedrive.definitions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.codec.binary.Hex;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import com.github.scribejava.apis.MicrosoftAzureActiveDirectory20Api;
import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.oauth.AuthorizationUrlBuilder;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.dtoservices.dtogenerator.annotations.AdditionalField;
import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gf.base.service.annotations.Field;
import net.datenwerke.gf.base.service.annotations.Indexed;
import net.datenwerke.rs.core.service.datasinkmanager.FolderedDatasink;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.oauth.service.oauth.OAuthAuthenticatable;
import net.datenwerke.rs.onedrive.service.onedrive.definitions.dtogen.OneDriveDatasink2DtoPostProcessor;
import net.datenwerke.rs.onedrive.service.onedrive.locale.OneDriveDatasinkMessages;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;
import net.datenwerke.security.service.crypto.pbe.PbeService;
import net.datenwerke.security.service.crypto.pbe.encrypt.EncryptionService;

/**
 * Used to define OneDrive datasinks that can be used in ReportServer to send
 * reports to a given OneDrive account.
 */
@Entity
@Table(name = "ONEDRIVE_DATASINK")
@Audited
@GenerateDto(
      dtoPackage = "net.datenwerke.rs.onedrive.client.onedrive.dto", 
      poso2DtoPostProcessors = OneDriveDatasink2DtoPostProcessor.class, 
      additionalFields = {
            @AdditionalField(name = "hasRefreshToken", type = Boolean.class),
            @AdditionalField(name = "hasSecretKey", type = Boolean.class) }, 
      icon = "cloud-upload"
      )
@InstanceDescription(
      msgLocation = OneDriveDatasinkMessages.class, 
      objNameKey = "oneDriveDatasinkTypeName", 
      icon = "cloud-upload"
      )
@Indexed
public class OneDriveDatasink extends DatasinkDefinition implements OAuthAuthenticatable, FolderedDatasink {
   /**
    * 
    */
   private static final long serialVersionUID = -4795824112931385206L;

   @Inject
   protected static Provider<PbeService> pbeServiceProvider;

   @ExposeToClient
   @Field
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   private String appKey;

   @ExposeToClient(exposeValueToClient = false, mergeDtoValueBack = true)
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   private String secretKey;

   @ExposeToClient(exposeValueToClient = false, mergeDtoValueBack = true)
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   private String refreshToken;
   
   @ExposeToClient
   @Field
   private String tenantId = "common";
   
   @ExposeToClient
   @Field
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   private String baseRoot = "/me/drive/items/root:";

   @ExposeToClient
   @Field
   @Column(length = 1024)
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
   
   public String getTenantId() {
      return tenantId;
   }

   public void setTenantId(String tenantId) {
      this.tenantId = tenantId;
   }
   
   public String getBaseRoot() {
      return baseRoot;
   }

   public void setBaseRoot(String baseRoot) {
      this.baseRoot = baseRoot;
   }

   /**
    * Encrypts and sets the given secret key.
    * 
    * @param secretKey the secret key to encrypt and set
    */

   public void setSecretKey(String secretKey) {
      if (null == secretKey)
         secretKey = "";

      EncryptionService encryptionService = pbeServiceProvider.get().getEncryptionService();
      byte[] encrypted = encryptionService.encrypt(secretKey);

      this.secretKey = new String(Hex.encodeHex(encrypted));
   }

   @Override
   public DefaultApi20 getOAuthApi() {
      return MicrosoftAzureActiveDirectory20Api.custom(tenantId);
   }

   @Override
   public String buildAuthorizationUrl(AuthorizationUrlBuilder authorizationUrlBuilder) {
      return authorizationUrlBuilder
            .scope("offline_access files.readwrite.all")
            .build();
   }

}