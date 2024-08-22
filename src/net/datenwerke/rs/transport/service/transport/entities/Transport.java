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
import com.google.inject.Inject;
import com.google.inject.Provider;

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
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Write;
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

   @Inject
   protected static Provider<SecurityService> securityServiceProvider;

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
   
   @Field
   @Column(length = 200)
   @ExposeToClient
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
   
   private transient boolean readAccess = false;
   private transient boolean writeAccess = false;
   private transient long lastRCache = 0;
   private transient long lastWCache = 0;
   
   public Transport() {
   }

   @SuppressWarnings("unchecked")   
   private void checkWritePermissions() {
      long current = System.currentTimeMillis();

      if (current - lastWCache > 5 * 60 * 1000)
         writeAccess = null != getId() ? securityServiceProvider.get().checkRights(this, Write.class) : true;

      if (!writeAccess) throw new ViolatedSecurityException(this, Write.class);
   }

   @SuppressWarnings("unchecked")   
   private void checkReadPermissions() {
      long current = System.currentTimeMillis();

      if (current - lastRCache > 5 * 60 * 1000)
         readAccess = null != getId() ? securityServiceProvider.get().checkRights(this, Read.class) : true;

      if (!readAccess) throw new ViolatedSecurityException(this, Read.class);
   }

   private void checkPreconditions() {
      if (null != getId() && closed)
         throw new IllegalStateException("Transport is already closed! It cannot be modified.");
   }
   
   public boolean isClosed() {
      checkReadPermissions();
      return closed;
   }

   public void setClosed(boolean closed) {
      checkWritePermissions();
      checkPreconditions();
      this.closed = closed;
      if (closed)
         this.status = TransportService.Status.CLOSED.name();
   }
   
   public void reopen() {
      checkWritePermissions();
      this.closed = false;
      this.status = TransportService.Status.CREATED.name();
         
   }
   
   public String getKey() {
      checkReadPermissions();
      return key;
   }

   public void setKey(String key) {
      checkWritePermissions();
      checkPreconditions();
      this.key = key;
   }

   @Override
   public String toString() {
      checkReadPermissions();
      return MoreObjects.toStringHelper(getClass())
            .add("key", key)
            .toString();
   }

   @Override
   public boolean hasChildren() {
      checkReadPermissions();
      return false;
   }

   @Override
   public String getName() {
      checkReadPermissions();
      return key;
   }
   
   public String getCreatorUsername() {
      checkReadPermissions();
      return creatorUsername;
   }
   
   public void setCreatorUsername(String creatorUsername) {
      checkWritePermissions();
      checkPreconditions();
      this.creatorUsername = creatorUsername;
   }

   public String getCreatorFirstname() {
      checkReadPermissions();
      return creatorFirstname;
   }

   public void setCreatorFirstname(String creatorFirstname) {
      checkWritePermissions();
      checkPreconditions();
      this.creatorFirstname = creatorFirstname;
   }

   public String getCreatorLastname() {
      checkReadPermissions();
      return creatorLastname;
   }

   public void setCreatorLastname(String creatorLastname) {
      checkWritePermissions();
      checkPreconditions();
      this.creatorLastname = creatorLastname;
   }

   public String getCreatorEmail() {
      checkReadPermissions();
      return creatorEmail;
   }

   public void setCreatorEmail(String creatorEmail) {
      checkWritePermissions();
      checkPreconditions();
      this.creatorEmail = creatorEmail;
   }

   public String getServerId() {
      checkReadPermissions();
      return serverId;
   }

   public void setServerId(String serverId) {
      checkWritePermissions();
      checkPreconditions();
      this.serverId = serverId;
   }

   public String getRsVersion() {
      checkReadPermissions();
      return rsVersion;
   }

   public void setRsVersion(String rsVersion) {
      checkWritePermissions();
      checkPreconditions();
      this.rsVersion = rsVersion;
   }

   public String getRsSchemaVersion() {
      checkReadPermissions();
      return rsSchemaVersion;
   }

   public void setRsSchemaVersion(String rsSchemaVersion) {
      checkWritePermissions();
      checkPreconditions();
      this.rsSchemaVersion = rsSchemaVersion;
   }

   public String getDescription() {
      checkReadPermissions();
      return description;
   }

   public void setDescription(String description) {
      checkWritePermissions();
      checkPreconditions();
      this.description = description;
   }

   public String getXml() {
      checkReadPermissions();
      return xml;
   }

   public void setXml(String xml) {
      checkWritePermissions();
      checkPreconditions();
      this.xml = xml;
   }

   public String getShortKey() {
      return key.substring(0, TransportModule.SHORT_KEY_LENGTH);
   }
   
   public String getCreatedOnStr() {
      checkReadPermissions();
      return DateUtils.formatLocal(getCreatedOn());
   }
   
   public void setStatus(String status) {
      checkWritePermissions();
      this.status = status;
   }
   
   public String getStatus() {
      checkReadPermissions();
      return status;
   }

   public Date getImportedOn() {
      checkReadPermissions();
      return importedOn;
   }

   public void setImportedOn(Date importedOn) {
      checkWritePermissions();
      this.importedOn = importedOn;
   }

   public User getImportedBy() {
      checkReadPermissions();
      return importedBy;
   }

   public void setImportedBy(User importedBy) {
      checkWritePermissions();
      this.importedBy = importedBy;
   }
   
   public String getImportedByStr() {
      checkReadPermissions();
      if (null == importedBy) return null;
      return importedBy.getName() + " (" + importedBy.getId() + ")";
   }
   
   public String getImportedOnStr() {
      checkReadPermissions();
      if (null == importedOn) return null;
      return DateUtils.formatLocal(getImportedOn());
   }

   public Date getAppliedOn() {
      checkReadPermissions();
      return appliedOn;
   }

   public void setAppliedOn(Date appliedOn) {
      checkWritePermissions();
      this.appliedOn = appliedOn;
   }
   
   public String getAppliedOnStr() {
      checkReadPermissions();
      if (null == appliedOn) return null;
      return DateUtils.formatLocal(getAppliedOn());
   }

   public User getAppliedBy() {
      checkReadPermissions();
      return appliedBy;
   }
   
   public String getAppliedByStr() {
      checkReadPermissions();
      if (null == appliedBy) return null;
      return appliedBy.getName() + " (" + appliedBy.getId() + ")";
   }

   public void setAppliedBy(User appliedBy) {
      checkWritePermissions();
      this.appliedBy = appliedBy;
   }

   public String getAppliedProtocol() {
      checkReadPermissions();
      return appliedProtocol;
   }

   public void setAppliedProtocol(String appliedProtocol) {
      checkWritePermissions();
      this.appliedProtocol = appliedProtocol;
   }
}
