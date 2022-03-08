package net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions;

import java.util.Arrays;
import java.util.List;

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
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.EmailDatasinkService;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.configs.DatasinkEmailConfig;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.dtogen.EmailDatasink2DtoPostProcessor;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.locale.EmailDatasinkMessages;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.crypto.pbe.PbeService;
import net.datenwerke.security.service.crypto.pbe.encrypt.EncryptionService;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * Used to define Email datasinks that can be used in ReportServer to send
 * reports to a given mail server.
 */
@Entity
@Table(name = "EMAIL_DATASINK")
@Audited
@GenerateDto(
      dtoPackage = "net.datenwerke.rs.emaildatasink.client.emaildatasink.dto", 
      poso2DtoPostProcessors = EmailDatasink2DtoPostProcessor.class, 
      additionalFields = {
            @AdditionalField(
                  name = "hasPassword", 
                  type = Boolean.class
                  ) 
            }, 
            icon = "send"
            )
@InstanceDescription(
      msgLocation = EmailDatasinkMessages.class, 
      objNameKey = "emailDatasinkTypeName", 
      icon = "send"
      )
@Indexed
public class EmailDatasink extends DatasinkDefinition {

   /**
    * 
    */
   private static final long serialVersionUID = -1430129744824205335L;

   @Inject
   protected static Provider<PbeService> pbeServiceProvider;
   
   @Inject
   protected static Provider<EmailDatasinkService> basicDatasinkService;
   
   @Inject
   protected static Provider<AuthenticatorService> authenticationService;

   @ExposeToClient
   @Field
   @Column(length = 1024)
   private String host = "smtp.host.net";

   @ExposeToClient
   @Field
   private int port = 25;

   @ExposeToClient
   @Field
   private String username;

   @ExposeToClient(exposeValueToClient = false, mergeDtoValueBack = true)
   private String password;

   @ExposeToClient
   @Field
   private boolean sslEnable;

   @ExposeToClient
   @Field
   private boolean tlsEnable;

   @ExposeToClient
   @Field
   private boolean tlsRequire;

   @ExposeToClient
   @Field
   private String sender;

   @ExposeToClient
   @Field
   private String senderName;

   @ExposeToClient
   @Field
   private boolean forceSender;

   @ExposeToClient
   @Field
   private String encryptionPolicy = "allow_mixed";

   public String getHost() {
      return host;
   }

   public void setHost(String host) {
      this.host = host;
   }

   public int getPort() {
      return port;
   }

   public void setPort(int port) {
      this.port = port;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public boolean isSslEnable() {
      return sslEnable;
   }

   public void setSslEnable(boolean sslEnable) {
      this.sslEnable = sslEnable;
   }

   public boolean isTlsEnable() {
      return tlsEnable;
   }

   public void setTlsEnable(boolean tlsEnable) {
      this.tlsEnable = tlsEnable;
   }

   public boolean isTlsRequire() {
      return tlsRequire;
   }

   public void setTlsRequire(boolean tlsRequire) {
      this.tlsRequire = tlsRequire;
   }

   public String getSender() {
      return sender;
   }

   public void setSender(String sender) {
      this.sender = sender;
   }

   public boolean isForceSender() {
      return forceSender;
   }

   public void setForceSender(boolean forceSender) {
      this.forceSender = forceSender;
   }

   public String getEncryptionPolicy() {
      return encryptionPolicy;
   }

   public void setEncryptionPolicy(String encryptionPolicy) {
      this.encryptionPolicy = encryptionPolicy;
   }

   public String getSenderName() {
      return senderName;
   }

   public void setSenderName(String senderName) {
      this.senderName = senderName;
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
      return new String(encryptionService.decryptFromHex(password));
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
      return new DatasinkEmailConfig() {

         @Override
         public String getFilename() {
            return "export.txt";
         }

         @Override
         public boolean isSendSyncEmail() {
            return true;
         }

         @Override
         public String getSubject() {
            return "ReportServer export";
         }

         @Override
         public List<User> getRecipients() {
            return Arrays.asList(authenticationService.get().getCurrentUser());
         }

         @Override
         public String getBody() {
            return "ReportServer export attached";
         }
      };
   }

}
