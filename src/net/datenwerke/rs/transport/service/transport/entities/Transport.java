package net.datenwerke.rs.transport.service.transport.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import com.google.common.base.MoreObjects;

import net.datenwerke.dtoservices.dtogenerator.annotations.AdditionalField;
import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.dtoservices.dtogenerator.annotations.PropertyValidator;
import net.datenwerke.dtoservices.dtogenerator.annotations.StringValidator;
import net.datenwerke.eximport.ex.annotations.ExportableField;
import net.datenwerke.gf.base.service.annotations.Field;
import net.datenwerke.gf.base.service.annotations.Indexed;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.keyutils.service.keyutils.KeyNameGeneratorService;
import net.datenwerke.rs.transport.service.transport.TransportModule;
import net.datenwerke.rs.transport.service.transport.TransportService;
import net.datenwerke.rs.transport.service.transport.entities.post.Transport2DtoPostProcessor;
import net.datenwerke.rs.transport.service.transport.locale.TransportManagerMessages;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.rs.utils.entitycloner.annotation.EntityClonerIgnore;
import net.datenwerke.rs.utils.instancedescription.annotations.Description;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;
import net.datenwerke.rs.utils.instancedescription.annotations.Title;
import net.datenwerke.rs.utils.misc.DateUtils;
import net.datenwerke.rs.utils.validator.shared.SharedRegex;
import net.datenwerke.security.service.usermanager.entities.User;

@Entity
@Table(name = "TRANSPORT")
@Audited
@Indexed
@InstanceDescription(
      msgLocation = TransportManagerMessages.class, 
      objNameKey = "transportTypeName",
      icon = "archive"
)
@GenerateDto(
      dtoPackage = "net.datenwerke.rs.transport.client.transport.dto", 
      createDecorator = true, 
      poso2DtoPostProcessors = Transport2DtoPostProcessor.class, 
      displayTitle = "getCreatedOnStr() + \"-\" + getShortKey()", 
      typeDescriptionMsg = net.datenwerke.rs.transport.client.transport.locale.TransportMessages.class, 
      typeDescriptionKey = "transport", 
      icon = "archive",
      additionalFields = {
            @AdditionalField(
                  name = "shortKey", 
                  type = String.class
            ),
            @AdditionalField(
                  name = "createdOnStr", 
                  type = String.class
            ),
            @AdditionalField(
                  name = "importedOnStr", 
                  type = String.class
            ),
            @AdditionalField(
                  name = "appliedOnStr", 
                  type = String.class
            ),
            @AdditionalField(
                  name = "importedByStr", 
                  type = String.class
            ),
            @AdditionalField(
                  name = "appliedByStr", 
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
   
   @ExposeToClient
   @Column(length = 8, nullable = false)
   @Field
   private String status;
   
   @ExposeToClient
   @Field
   @ExportableField(exportField = false)
   private Date importedOn;
   
   @ExposeToClient
   @ManyToOne
   @Field
   @EnclosedEntity
   @ExportableField(exportField = false)
   private User importedBy;
   
   @ExposeToClient
   @Field
   @ExportableField(exportField = false)
   private Date appliedOn;
   
   @ExposeToClient
   @ManyToOne
   @Field
   @EnclosedEntity
   @ExportableField(exportField = false)
   private User appliedBy;
   
   @Lob
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   @ExposeToClient(allowArbitraryLobSize = true, disableHtmlEncode = true, exposeValueToClient = false)
   @ExportableField(exportField = false)
   private String appliedProtocol;
   
   @Lob
   @Field
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   @ExposeToClient(allowArbitraryLobSize = true, exposeValueToClient = true)
   @Description
   private String description;
   
   @Lob
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   @ExposeToClient(allowArbitraryLobSize = true, disableHtmlEncode = true, exposeValueToClient = false)
   private String xml;
   
   @ExposeToClient(
         view = DtoView.LIST, 
         validateDtoProperty = @PropertyValidator(
               string = @StringValidator(
                     regex = SharedRegex.KEY_REGEX
               )
         )
   )
   @Field
   @Column(
         length = KeyNameGeneratorService.KEY_LENGTH,
         unique = true,
         nullable = false
   )
   @EntityClonerIgnore
   @Title
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
      if (closed)
         this.status = TransportService.Status.CLOSED.name();
   }
   
   public void reopen() {
      this.closed = false;
      this.status = TransportService.Status.CREATED.name();
         
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
   
   public void setStatus(String status) {
      this.status = status;
   }
   
   public String getStatus() {
      return status;
   }

   public Date getImportedOn() {
      return importedOn;
   }

   public void setImportedOn(Date importedOn) {
      this.importedOn = importedOn;
   }

   public User getImportedBy() {
      return importedBy;
   }

   public void setImportedBy(User importedBy) {
      this.importedBy = importedBy;
   }
   
   public String getImportedByStr() {
      if (null == importedBy) return null;
      return importedBy.getName() + " (" + importedBy.getId() + ")";
   }
   
   public String getImportedOnStr() {
      if (null == importedOn) return null;
      return DateUtils.formatLocal(getImportedOn());
   }

   public Date getAppliedOn() {
      return appliedOn;
   }

   public void setAppliedOn(Date appliedOn) {
      this.appliedOn = appliedOn;
   }
   
   public String getAppliedOnStr() {
      if (null == appliedOn) return null;
      return DateUtils.formatLocal(getAppliedOn());
   }

   public User getAppliedBy() {
      return appliedBy;
   }
   
   public String getAppliedByStr() {
      if (null == appliedBy) return null;
      return appliedBy.getName() + " (" + appliedBy.getId() + ")";
   }

   public void setAppliedBy(User appliedBy) {
      this.appliedBy = appliedBy;
   }

   public String getAppliedProtocol() {
      return appliedProtocol;
   }

   public void setAppliedProtocol(String appliedProtocol) {
      this.appliedProtocol = appliedProtocol;
   }
}
