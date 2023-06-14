package net.datenwerke.rs.transport.service.transport.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import com.google.common.base.MoreObjects;

import net.datenwerke.dtoservices.dtogenerator.annotations.AdditionalField;
import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.dtoservices.dtogenerator.annotations.PropertyValidator;
import net.datenwerke.dtoservices.dtogenerator.annotations.StringValidator;
import net.datenwerke.gf.base.service.annotations.Field;
import net.datenwerke.gf.base.service.annotations.Indexed;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.transport.service.transport.TransportModule;
import net.datenwerke.rs.transport.service.transport.entities.post.Transport2DtoPostProcessor;
import net.datenwerke.rs.utils.entitycloner.annotation.EntityClonerIgnore;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;
import net.datenwerke.rs.utils.misc.DateUtils;
import net.datenwerke.security.service.usermanager.locale.UserManagerMessages;

@Entity
@Table(name = "TRANSPORT")
@Audited
@Indexed
@InstanceDescription(
      msgLocation = UserManagerMessages.class, 
      objNameKey = "userTypeName",
      title = "${shortKey}", 
      fields = { "shortKey" }, 
      icon = "archive"
)
@GenerateDto(
      dtoPackage = "net.datenwerke.rs.transport.client.transport.dto", 
      createDecorator = true, 
      poso2DtoPostProcessors = Transport2DtoPostProcessor.class, 
      displayTitle = "getCreatedOnStr() + \"-\" + getShortKey()", 
      typeDescriptionMsg = net.datenwerke.security.client.locale.UserManagerMessages.class, 
      typeDescriptionKey = "user", 
      icon = "archive",
      additionalFields = {
            @AdditionalField(
                  name = "shortKey", 
                  type = String.class
            ),
            @AdditionalField(
                  name = "createdOnStr", 
                  type = String.class
            ) 
      }
)
public class Transport extends AbstractTransportManagerNode {

   /**
    * 
    */
   private static final long serialVersionUID = 3056683014040311065L;

   @ExposeToClient
   @Field
   private boolean closed = false;

   @ExposeToClient
   @Column(length = 128)
   @Field
   private String creatorUsername;
   
   @ExposeToClient
   @Column(length = 128)
   @Field
   private String creatorFirstname;
   
   @ExposeToClient
   @Column(length = 128)
   @Field
   private String creatorLastname;
   
   @ExposeToClient
   @Column(length = 320)
   @Field
   private String creatorEmail;
   
   @ExposeToClient
   @Column(length = 50)
   @Field
   private String serverId;
   
   @ExposeToClient
   @Column(length = 50)
   @Field
   private String rsVersion;
   
   @ExposeToClient
   @Column(length = 20)
   @Field
   private String rsSchemaVersion;
   
   @Lob
   @Field
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   @ExposeToClient(allowArbitraryLobSize = true, exposeValueToClient = true)
   private String description;
   
   @Lob
   @Field
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   @ExposeToClient(allowArbitraryLobSize = true, disableHtmlEncode = true, exposeValueToClient = false)
   private String xml;
   
   @ExposeToClient(
         view = DtoView.LIST, 
         validateDtoProperty = @PropertyValidator(
               string = @StringValidator(
                     regex = "^[a-zA-Z0-9_\\-]*$"
               )
         )
   )
   @Field
   @Column(
         length = 50,
         unique = true
   )
   @EntityClonerIgnore
   private String key;
   
   public Transport() {
   }

   private void checkPreconditions() {
      if (null != getId() && closed)
         throw new IllegalStateException("Transport is already closed! It cannot be modified.");
   }
   
   public boolean isClosed() {
      return closed;
   }

   public void setClosed(boolean closed) {
      checkPreconditions();
      this.closed = closed;
   }
   
   public String getKey() {
      return key;
   }

   public void setKey(String key) {
      checkPreconditions();
      this.key = key;
   }

   @Override
   public String toString() {
      return MoreObjects.toStringHelper(getClass())
            .add("key", key)
            .toString();
   }

   @Override
   public boolean hasChildren() {
      return false;
   }

   @Override
   public String getName() {
      return key;
   }
   
   public String getCreatorUsername() {
      return creatorUsername;
   }
   
   public void setCreatorUsername(String creatorUsername) {
      checkPreconditions();
      this.creatorUsername = creatorUsername;
   }

   public String getCreatorFirstname() {
      return creatorFirstname;
   }

   public void setCreatorFirstname(String creatorFirstname) {
      checkPreconditions();
      this.creatorFirstname = creatorFirstname;
   }

   public String getCreatorLastname() {
      return creatorLastname;
   }

   public void setCreatorLastname(String creatorLastname) {
      checkPreconditions();
      this.creatorLastname = creatorLastname;
   }

   public String getCreatorEmail() {
      return creatorEmail;
   }

   public void setCreatorEmail(String creatorEmail) {
      checkPreconditions();
      this.creatorEmail = creatorEmail;
   }

   public String getServerId() {
      return serverId;
   }

   public void setServerId(String serverId) {
      checkPreconditions();
      this.serverId = serverId;
   }

   public String getRsVersion() {
      return rsVersion;
   }

   public void setRsVersion(String rsVersion) {
      checkPreconditions();
      this.rsVersion = rsVersion;
   }

   public String getRsSchemaVersion() {
      return rsSchemaVersion;
   }

   public void setRsSchemaVersion(String rsSchemaVersion) {
      checkPreconditions();
      this.rsSchemaVersion = rsSchemaVersion;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      checkPreconditions();
      this.description = description;
   }

   public String getXml() {
      return xml;
   }

   public void setXml(String xml) {
      checkPreconditions();
      this.xml = xml;
   }

   public String getShortKey() {
      return key.substring(0, TransportModule.SHORT_KEY_LENGTH);
   }
   
   public String getCreatedOnStr() {
      return DateUtils.formatLocal(getCreatedOn());
   }
   
}
