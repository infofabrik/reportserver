package net.datenwerke.rs.amazons3.service.amazons3.definitions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.codec.binary.Hex;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.dtoservices.dtogenerator.annotations.AdditionalField;
import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gf.base.service.annotations.Field;
import net.datenwerke.gf.base.service.annotations.Indexed;
import net.datenwerke.rs.amazons3.service.amazons3.definitions.dtogen.AmazonS3Datasink2DtoPostProcessor;
import net.datenwerke.rs.amazons3.service.amazons3.locale.AmazonS3DatasinkMessages;
import net.datenwerke.rs.core.service.datasinkmanager.FolderedDatasink;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;
import net.datenwerke.security.service.crypto.pbe.PbeService;
import net.datenwerke.security.service.crypto.pbe.encrypt.EncryptionService;


/**
 * Used to define AmazonS3 datasinks that can be used in ReportServer to send
 * reports to a given drop box account.
 */
@Entity
@Table(name = "AMAZONS3_DATASINK")
@Audited
@GenerateDto(
      dtoPackage = "net.datenwerke.rs.amazons3.client.amazons3.dto", 
      poso2DtoPostProcessors = AmazonS3Datasink2DtoPostProcessor.class, 
      additionalFields = {
            @AdditionalField(name = "hasSecretKey", type = Boolean.class) 
      }, 
      icon = "amazon")
@InstanceDescription(
      msgLocation = AmazonS3DatasinkMessages.class, 
      objNameKey = "amazonS3DatasinkTypeName", 
      icon = "amazon")
@Indexed
public class AmazonS3Datasink extends DatasinkDefinition implements FolderedDatasink {

   /**
    * 
    */
   private static final long serialVersionUID = 1080828242099292320L;

   @Inject
   protected static Provider<PbeService> pbeServiceProvider;

   @ExposeToClient
   @Field
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   private String appKey;

   @ExposeToClient(exposeValueToClient = false, mergeDtoValueBack = true)
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   private String secretKey;

   @ExposeToClient
   @Field
   @Column(length = 1024)
   private String folder = "/";

   @ExposeToClient
   @Field
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   private String bucketName;

   @ExposeToClient
   @Field
   @Column(length = 1024)
   private String regionName;
   
   @ExposeToClient
   @Field
   private String storageType;

   public String getStorageType() {
      return storageType;
   }

   public void setStorageType(String storageType) {
      this.storageType = storageType;
   }

   public String getBucketName() {
      return bucketName;
   }

   public void setBucketName(String bucketName) {
      this.bucketName = bucketName;
   }

   public String getRegionName() {
      return regionName;
   }

   public void setRegionName(String regionName) {
      this.regionName = regionName;
   }

   @Override
   public String getFolder() {
      return folder;
   }

   public void setFolder(String folder) {
      this.folder = folder;
   }

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

}
