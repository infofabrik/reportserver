package net.datenwerke.rs.tsreportarea.service.tsreportarea.entities;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.AdditionalField;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gf.base.service.annotations.Field;
import net.datenwerke.gf.base.service.annotations.Indexed;
import net.datenwerke.rs.scheduleasfile.service.scheduleasfile.locale.ScheduleAsFileMessages;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.post.TsDiskFileReference2DtoPost;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;

@Entity
@Table(name = "TS_DISK_FILE_REFERENCE")
@Audited
@Indexed
@GenerateDto(
      dtoPackage = "net.datenwerke.rs.tsreportarea.client.tsreportarea.dto", 
      createDecorator = true, 
      poso2DtoPostProcessors = TsDiskFileReference2DtoPost.class, 
      additionalFields = {
            @AdditionalField(
                  name = "iconStr", 
                  type = String.class
            ),
            @AdditionalField(
                  name = "typeStr", 
                  type = String.class
            ), 
      }
)
@InstanceDescription(
      msgLocation = ScheduleAsFileMessages.class, 
      objNameKey = "reportTypeName"
)
public class TsDiskFileReference extends TsDiskGeneralReference {

   /**
    * 
    */
   private static final long serialVersionUID = 1319125670340405303L;

   @Lob
   @Basic(fetch = FetchType.LAZY)
   private byte[] data;

   @Field
   private String filename;
   
   @Transient
   private String contentType;
   
   public void setData(byte[] data) {
      this.data = data;
   }

   public String getFileName() {
      return filename;
   }

   public void setFileName(String filename) {
      this.filename = filename;
   }

   @Override
   public Date getReferenceLastUpdated() {
      return getLastUpdated();
   }

   @Override
   public boolean hasData() {
      return null != data;
   }

   @Override
   public byte[] getData() {
      return data;
   }

   @Override
   public long getSize() {
      return data.length;
   }

   @Override
   public String getContentType() {
      return contentType;
   }

   public void setContentType(String contentType) {
      this.contentType = contentType;
   }
}
