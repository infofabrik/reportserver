package net.datenwerke.rs.remotersserver.service.remotersserver.entities;

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
import net.datenwerke.rs.remotersserver.service.remotersserver.entities.dtogen.RemoteRsServer2DtoPostProcessor;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.RemoteServerDefinition;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.locale.RemoteServerManagerMessages;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;
import net.datenwerke.security.service.crypto.pbe.PbeService;
import net.datenwerke.security.service.crypto.pbe.encrypt.EncryptionService;


/**
 * Used to define a connection to the external RS server
 */

@Entity
@Table(name = "REMOTE_RS_SERVER")
@Audited
@GenerateDto(
      dtoPackage = "net.datenwerke.rs.remotersserver.client.remotersserver.dto", 
      poso2DtoPostProcessors = RemoteRsServer2DtoPostProcessor.class, 
      additionalFields = {
            @AdditionalField(
                  name = "hasApikey", 
                  type = Boolean.class
                  )
            }, 
            icon = "laptop"
            )
@InstanceDescription(
      msgLocation = RemoteServerManagerMessages.class, 
      objNameKey = "remoteRsServer",
      icon = "laptop"
      )
@Indexed
public class RemoteRsServer extends RemoteServerDefinition {

   /**
    * 
    */
   private static final long serialVersionUID = 5532424176260294397L;
   

   @Inject
   protected static Provider<PbeService> pbeServiceProvider;

   @ExposeToClient
   @Field
   @Column(length = 1024)
   private String url = "";

   @ExposeToClient(exposeValueToClient = false, mergeDtoValueBack = true)
   private String apikey = "";
   
   @ExposeToClient
   @Field
   private String username;


   public String getUrl() {
      return url;
   }

   public void setUrl(String url) {
      this.url = url;
   }
   
   /**
    * Gets the decrypted apikey.
    * 
    * @return the decrypted apikey
    */
   public String getApikey() {
      if (null == apikey)
         return null;
      if ("".equals(apikey))
         return "";

      EncryptionService encryptionService = pbeServiceProvider.get().getEncryptionService();
      return new String(encryptionService.decryptFromHex(apikey));
   }

   /**
    * Encrypts and sets the given apikey.
    * 
    * @param apikey the apikey to encrypt and set
    */
   public void setApikey(String apikey) {
      if (null == apikey)
         apikey = "";

      EncryptionService encryptionService = pbeServiceProvider.get().getEncryptionService();
      byte[] encrypted = encryptionService.encrypt(apikey);

      this.apikey = new String(Hex.encodeHex(encrypted));
   }
   

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

}