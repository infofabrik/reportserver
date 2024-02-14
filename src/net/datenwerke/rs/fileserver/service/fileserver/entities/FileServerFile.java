package net.datenwerke.rs.fileserver.service.fileserver.entities;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
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
import net.datenwerke.rs.fileserver.client.fileserver.locale.FileServerMessages;
import net.datenwerke.rs.fileserver.service.fileserver.entities.post.File2DtoPostProcessor;
import net.datenwerke.rs.fileserver.service.fileserver.locale.FileserverMessages;
import net.datenwerke.rs.keyutils.service.keyutils.KeyNameGeneratorService;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.rs.utils.entitymerge.service.annotations.EntityMergeField;
import net.datenwerke.rs.utils.instancedescription.annotations.Description;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;
import net.datenwerke.rs.utils.instancedescription.annotations.Title;
import net.datenwerke.rs.utils.validator.shared.SharedRegex;

@Entity
@Table(name = "FILE_SERVER_FILE")
@Audited
@Indexed
@GenerateDto(dtoPackage = "net.datenwerke.rs.fileserver.client.fileserver.dto", createDecorator = true, poso2DtoPostProcessors = File2DtoPostProcessor.class, typeDescriptionMsg = FileServerMessages.class, typeDescriptionKey = "file", additionalFields = {
      @AdditionalField(name = "size", type = Integer.class) })
@InstanceDescription(msgLocation = FileserverMessages.class, objNameKey = "fileTypeName", icon = "file")
@Cacheable(false)
@Cache(usage = CacheConcurrencyStrategy.NONE)
public class FileServerFile extends AbstractFileServerNode {

   /**
    * 
    */
   private static final long serialVersionUID = -2324696384754535610L;

   @ExposeToClient(view = DtoView.MINIMAL, displayTitle = true)
   @Column(length = 128)
   @Field
   @Title
   @EntityMergeField
   private String name;
   
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
   private String key;

   @ExposeToClient(view = DtoView.MINIMAL)
   @Lob
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   @Field
   @Description
   @EntityMergeField
   private String description;

   @ExposeToClient
   @Column(length = 128)
   @EntityMergeField
   private String contentType;

   @EnclosedEntity
   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   @EntityMergeField(toClone = true)
   private FileServerFileData fileData = new FileServerFileData();

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
   
   public String getKey() {
      return key;
   }
   public void setKey(String key) {
      this.key = key;
   }


   public void setDescription(String description) {
      this.description = description;
   }

   public String getDescription() {
      return description;
   }

   public void setData(byte[] data) {
      getFileData().setData(data);
   }

   public byte[] getData() {
      return getFileData().getData();
   }

   public void setContentType(String contentType) {
      this.contentType = contentType;
   }

   public String getContentType() {
      return contentType;
   }

   public FileServerFileData getFileData() {
      return fileData;
   }

   public long getSize() {
      if (null == fileData)
         return 0;
      return fileData.getSize();
   }

   @Override
   public boolean hasChildren() {
      return false;
   }
   
   @Override
   public String toString() {
       return MoreObjects.toStringHelper(getClass())
             .add("ID", getIdOrOldTransient())
             .add("Key", key)
             .add("Name", name)
             .toString();
   }

}
