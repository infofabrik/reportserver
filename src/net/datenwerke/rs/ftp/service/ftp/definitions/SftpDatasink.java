package net.datenwerke.rs.ftp.service.ftp.definitions;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
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
import net.datenwerke.rs.ftp.service.ftp.definitions.dtogen.SftpDatasink2DtoPostProcessor;
import net.datenwerke.rs.ftp.service.ftp.locale.FtpMessages;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;
import net.datenwerke.security.service.crypto.pbe.PbeService;
import net.datenwerke.security.service.crypto.pbe.encrypt.EncryptionService;

/**
 * Used to define SFTP datasinks that can be used in ReportServer to send
 * reports to a given SFTP server.
 */
@Entity
@Table(name = "SFTP_DATASINK")
@Audited
@GenerateDto(
      dtoPackage = "net.datenwerke.rs.ftp.client.ftp.dto", 
      poso2DtoPostProcessors = SftpDatasink2DtoPostProcessor.class, 
      additionalFields = {
            @AdditionalField(name = "hasPassword", type = Boolean.class),
            @AdditionalField(name = "hasPrivateKeyPassphrase", type = Boolean.class), 
      }, 
      icon = "server")
@InstanceDescription(
      msgLocation = FtpMessages.class, 
      objNameKey = "sftpDatasinkTypeName",
      icon = "arrow-circle-up")
@Indexed
public class SftpDatasink extends DatasinkDefinition implements BasicDatasink {

   /**
    * 
    */
   private static final long serialVersionUID = 5532424176260294397L;

   @Inject
   protected static Provider<PbeService> pbeServiceProvider;

   @ExposeToClient
   @Field
   @Column(length = 1024)
   private String host = "sftp://ftp.host.net";

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

   @Basic(fetch = FetchType.LAZY)
   @Lob
   private byte[] privateKey;

   @ExposeToClient(exposeValueToClient = false, mergeDtoValueBack = true)
   private String privateKeyPassphrase;

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

   /**
    * Gets the decrypted private key bytes.
    * 
    * @return decrypted private key bytes
    */
   public byte[] getPrivateKey() {
      if (null == privateKey || 0 == privateKey.length)
         return null;

      return pbeServiceProvider.get().getEncryptionService().decrypt(privateKey);
   }

   /**
    * Sets and encrypts the given private key.
    * 
    * @param privateKey private key bytes
    */
   public void setPrivateKey(byte[] privateKey) {
      if (null == privateKey || 0 == privateKey.length) {
         this.privateKey = null;
         return;
      }

      this.privateKey = pbeServiceProvider.get().getEncryptionService().encrypt(privateKey);
   }

   /**
    * Gets the decrypted private key passphrase. May be null if the private key
    * does not contain a passphrase.
    * 
    * @return the decrypted private key passphrase or null if the private key does
    *         not contain a passphrase.
    */
   public String getPrivateKeyPassphrase() {
      if (null == privateKeyPassphrase)
         return null;
      if ("".equals(privateKeyPassphrase))
         return "";

      EncryptionService encryptionService = pbeServiceProvider.get().getEncryptionService();
      return new String(encryptionService.decryptFromHex(privateKeyPassphrase));
   }

   /**
    * Sets and encrypts the private key passphrase. Null is a valid value if the
    * private key does not contain a passphrase.
    * 
    * @param privateKeyPassphrase the private key passphrase.
    */
   public void setPrivateKeyPassphrase(String privateKeyPassphrase) {
      if (null == privateKeyPassphrase) {
         this.privateKeyPassphrase = null;
         return;
      }

      EncryptionService encryptionService = pbeServiceProvider.get().getEncryptionService();
      byte[] encrypted = encryptionService.encrypt(privateKeyPassphrase);

      this.privateKeyPassphrase = new String(Hex.encodeHex(encrypted));
   }

}
