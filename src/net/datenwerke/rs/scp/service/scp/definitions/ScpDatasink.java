package net.datenwerke.rs.scp.service.scp.definitions;

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
import net.datenwerke.rs.core.service.datasinkmanager.BasicDatasink;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.scp.service.scp.definitions.dtogen.ScpDatasink2DtoPostProcessor;
import net.datenwerke.rs.scp.service.scp.locale.ScpMessages;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;
import net.datenwerke.security.service.crypto.pbe.PbeService;
import net.datenwerke.security.service.crypto.pbe.encrypt.EncryptionService;

/**
 * Used to define SCP datasinks that can be used in ReportServer to send reports
 * to a given SCP server.
 */
@Entity
@Table(name = "SCP_DATASINK")
@Audited
@GenerateDto(
      dtoPackage = "net.datenwerke.rs.scp.client.scp.dto", 
      poso2DtoPostProcessors = ScpDatasink2DtoPostProcessor.class, 
      additionalFields = {
            @AdditionalField(name = "hasPassword", 
                  type = Boolean.class), 
            }, 
            icon = "arrow-up")
   @InstanceDescription(
         msgLocation = ScpMessages.class, 
         objNameKey = "scpDatasinkTypeName",
         icon = "arrow-up")
@Indexed
public class ScpDatasink extends DatasinkDefinition implements BasicDatasink {

   /**
    * 
    */
   private static final long serialVersionUID = -5360244136342094659L;

   @Inject
   protected static Provider<PbeService> pbeServiceProvider;

   @ExposeToClient
   @Field
   @Column(length = 1024)
   private String host = "scp://scp.host.net";

   @ExposeToClient
   @Field
   private int port = 22;

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
   
   public String getAuthenticationType() {
      return authenticationType;
   }

   public void setAuthenticationType(String authenticationType) {
      this.authenticationType = authenticationType;
   }

}
