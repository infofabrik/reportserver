package net.datenwerke.rs.box.service.box.definitions;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.codec.binary.Hex;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import com.github.scribejava.apis.BoxApi20;
import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.oauth.AuthorizationUrlBuilder;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.dtoservices.dtogenerator.annotations.AdditionalField;
import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gf.base.service.annotations.Field;
import net.datenwerke.gf.base.service.annotations.Indexed;
import net.datenwerke.rs.box.service.box.definitions.dtogen.BoxDatasink2DtoPostProcessor;
import net.datenwerke.rs.box.service.box.locale.BoxDatasinkMessages;
import net.datenwerke.rs.core.service.datasinkmanager.FolderedDatasink;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.oauth.service.oauth.OAuthAuthenticatable;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;
import net.datenwerke.security.service.crypto.pbe.PbeService;
import net.datenwerke.security.service.crypto.pbe.encrypt.EncryptionService;

/**
 * Used to define Box datasinks that can be used in ReportServer to send reports
 * to a given box account.
 */
@Entity
@Table(name = "BOX_DATASINK")
@Audited
@GenerateDto(dtoPackage = "net.datenwerke.rs.box.client.box.dto", poso2DtoPostProcessors = BoxDatasink2DtoPostProcessor.class, additionalFields = {
      @AdditionalField(name = "hasRefreshToken", type = Boolean.class),
      @AdditionalField(name = "hasSecretKey", type = Boolean.class) }, icon = "cube")
@InstanceDescription(msgLocation = BoxDatasinkMessages.class, objNameKey = "boxDatasinkTypeName", icon = "cube")
@Indexed
public class BoxDatasink extends DatasinkDefinition implements OAuthAuthenticatable, FolderedDatasink {

   /**
    * 
    */
   private static final long serialVersionUID = 8930485671849043989L;

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

   /**
    * Encrypts and sets the given secret
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
      return BoxApi20.instance();
   }

   @Override
   public String buildAuthorizationUrl(AuthorizationUrlBuilder authorizationUrlBuilder) {
      Map<String, String> additionalParameters = new HashMap<>();
      additionalParameters.put("access_type", "offline");
      additionalParameters.put("prompt", "consent");
      return authorizationUrlBuilder.additionalParams(additionalParameters).build();
   }

}

