package net.datenwerke.rs.ftp.service.ftp.definitions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.codec.binary.Hex;
import org.hibernate.envers.Audited;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.dtoservices.dtogenerator.annotations.AdditionalField;
import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gf.base.service.annotations.Field;
import net.datenwerke.gf.base.service.annotations.Indexed;
import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.FolderedDatasink;
import net.datenwerke.rs.core.service.datasinkmanager.HostDatasink;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkFilenameFolderConfig;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.ftp.service.ftp.FtpsService;
import net.datenwerke.rs.ftp.service.ftp.definitions.dtogen.FtpsDatasink2DtoPostProcessor;
import net.datenwerke.rs.ftp.service.ftp.locale.FtpMessages;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;
import net.datenwerke.rs.utils.misc.DateUtils;
import net.datenwerke.security.service.crypto.pbe.PbeService;
import net.datenwerke.security.service.crypto.pbe.encrypt.EncryptionService;

/**
 * Used to define FTPS datasinks that can be used in ReportServer to send
 * reports to a given FTPS server.
 */
@Entity
@Table(name = "FTPS_DATASINK")
@Audited
@GenerateDto(
      dtoPackage = "net.datenwerke.rs.ftp.client.ftp.dto", 
      poso2DtoPostProcessors = FtpsDatasink2DtoPostProcessor.class, 
      additionalFields = {
            @AdditionalField(
                  name = "hasPassword", 
                  type = Boolean.class
                  ),
            @AdditionalField(
                  name = "hasPrivateKeyPassphrase", 
                  type = Boolean.class
                  ) 
            }, 
            icon = "arrow-circle-o-up"
            )
@InstanceDescription(
      msgLocation = FtpMessages.class, 
      objNameKey = "ftpsDatasinkTypeName", 
      icon = "arrow-circle-o-up"
      )
@Indexed
public class FtpsDatasink extends DatasinkDefinition implements HostDatasink, FolderedDatasink {

   /**
      * 
      */
   private static final long serialVersionUID = 5532424176260294397L;

   @Inject
   protected static Provider<PbeService> pbeServiceProvider;
   
   @Inject
   protected static Provider<FtpsService> basicDatasinkService;

   @ExposeToClient
   @Field
   @Column(length = 1024)
   private String host = "ftps://ftps.host.net";

   @ExposeToClient
   @Field
   private int port = 21;

   @ExposeToClient
   @Field
   private String username;

   @ExposeToClient
   private String authenticationType;

   @ExposeToClient(exposeValueToClient = false, mergeDtoValueBack = true)
   private String password;

   @ExposeToClient
   @Field
   @Column(length = 1024)
   private String folder = "./";

   /**
    * Active / passive mode.
    */
   @ExposeToClient
   @Field
   private String ftpMode;

   @ExposeToClient
   @Field
   private String dataChannelProtectionLevel;

   @Override
   public String getHost() {
      return host;
   }

   @Override
   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   @Override
   public int getPort() {
      return port;
   }

   public void setPort(int port) {
      this.port = port;
   }

   @Override
   public String getFolder() {
      return folder;
   }

   public void setFolder(String folder) {
      this.folder = folder;
   }

   public void setHost(String host) {
      this.host = host;
   }

   public String getFtpMode() {
      return ftpMode;
   }

   public void setFtpMode(String mode) {
      this.ftpMode = mode;
   }

   public String getDataChannelProtectionLevel() {
      return dataChannelProtectionLevel;
   }

   public void setDataChannelProtectionLevel(String dataChannelProtectionLevel) {
      this.dataChannelProtectionLevel = dataChannelProtectionLevel;
   }

   /**
    * Gets the decrypted password.
    * 
    * @return the decrypted password
    */
   public String getPassword() {
      if (null == password)
         return null;
      if ("".equals(password))
         return "";

      EncryptionService encryptionService = pbeServiceProvider.get().getEncryptionService();
      String decrypted = new String(encryptionService.decryptFromHex(password));
      return decrypted;
   }

   /**
    * Encrypts and sets the password.
    * 
    * @param password the password to encrypt and set.
    */
   public void setPassword(String password) {
      if (null == password)
         password = "";

      EncryptionService encryptionService = pbeServiceProvider.get().getEncryptionService();
      byte[] encrypted = encryptionService.encrypt(password);

      this.password = new String(Hex.encodeHex(encrypted));
   }

   public String getAuthenticationType() {
      return authenticationType;
   }

   public void setAuthenticationType(String authenticationType) {
      this.authenticationType = authenticationType;
   }

   @Override
   public BasicDatasinkService getDatasinkService() {
      return basicDatasinkService.get();
   }

   @Override
   public DatasinkConfiguration getDefaultConfiguration() {
      return new DatasinkFilenameFolderConfig() {

         @Override
         public String getFolder() {
            return folder;
         }

         @Override
         public String getFilename() {
            return DEFAULT_EXPORT_FILENAME + DateUtils.formatCurrentDate() + ".txt";
         }
      };
   }

}