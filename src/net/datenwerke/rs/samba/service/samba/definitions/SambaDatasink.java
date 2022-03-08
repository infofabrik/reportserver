package net.datenwerke.rs.samba.service.samba.definitions;

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
import net.datenwerke.rs.samba.service.samba.SambaService;
import net.datenwerke.rs.samba.service.samba.definitions.dtogen.SambaDatasink2DtoPostProcessor;
import net.datenwerke.rs.samba.service.samba.locale.SambaMessages;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;
import net.datenwerke.security.service.crypto.pbe.PbeService;
import net.datenwerke.security.service.crypto.pbe.encrypt.EncryptionService;

/**
 * Used to define Samba datasinks that can be used in ReportServer to send
 * reports to a given Samba server.
 */
@Entity
@Table(name = "SAMBA_DATASINK")
@Audited
@GenerateDto(
      dtoPackage = "net.datenwerke.rs.samba.client.samba.dto", 
      poso2DtoPostProcessors = SambaDatasink2DtoPostProcessor.class, 
      additionalFields = {
            @AdditionalField(
                  name = "hasPassword", 
                  type = Boolean.class
                  )
            }, 
            icon = "angle-double-up"
            )
@InstanceDescription(
      msgLocation = SambaMessages.class, 
      objNameKey = "sambaDatasinkTypeName",
      icon = "angle-double-up"
      )
@Indexed
public class SambaDatasink extends DatasinkDefinition implements HostDatasink, FolderedDatasink {

   /**
    * 
    */
   private static final long serialVersionUID = 5532424176260294397L;

   @Inject
   protected static Provider<PbeService> pbeServiceProvider;
   
   @Inject
   protected static Provider<SambaService> basicDatasinkService;

   @ExposeToClient
   @Field
   @Column(length = 1024)
   private String host = "smb://smb.host.net";

   @ExposeToClient
   @Field
   private int port = 445;

   @ExposeToClient
   @Field
   private String domain;

   @ExposeToClient
   @Field
   private String username;

   @ExposeToClient(exposeValueToClient = false, mergeDtoValueBack = true)
   private String password;

   @ExposeToClient
   @Field
   @Column(length = 1024)
   private String folder = "./";

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

   public String getDomain() {
      return domain;
   }

   public void setDomain(String domain) {
      this.domain = domain;
   }

   public void setHost(String host) {
      this.host = host;
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
    * Encrypts and sets the given password.
    * 
    * @param password the password to encrypt and set
    */
   public void setPassword(String password) {
      if (null == password)
         password = "";

      EncryptionService encryptionService = pbeServiceProvider.get().getEncryptionService();
      byte[] encrypted = encryptionService.encrypt(password);

      this.password = new String(Hex.encodeHex(encrypted));
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
            return "export.txt";
         }
      };
   }

}
