package net.datenwerke.rs.tsreportarea.service.tsreportarea.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gf.base.service.annotations.Field;
import net.datenwerke.gf.base.service.annotations.Indexed;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.locale.TsDiskMessages;
import net.datenwerke.rs.utils.instancedescription.annotations.Description;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;
import net.datenwerke.rs.utils.instancedescription.annotations.Title;
import net.datenwerke.treedb.service.treedb.annotation.TreeDBAllowedChildren;

@Entity
@Table(name = "TS_DISK_FOLDER")
@Audited
@Indexed
@TreeDBAllowedChildren({ TsDiskFolder.class, TsDiskReportReference.class, TsDiskGeneralReference.class })
@GenerateDto(dtoPackage = "net.datenwerke.rs.tsreportarea.client.tsreportarea.dto", typeDescriptionMsg = BaseMessages.class, typeDescriptionKey = "folder", icon = "folder")
@InstanceDescription(msgLocation = TsDiskMessages.class, objNameKey = "tsFolderTypeName", icon = "folder")
public class TsDiskFolder extends AbstractTsDiskNode {

   /**
    * 
    */
   private static final long serialVersionUID = 4539377247922399820L;

   @ExposeToClient(view = DtoView.MINIMAL, displayTitle = true)
   @Column(length = 128)
   @Field
   @Title
   private String name;

   @ExposeToClient(view = DtoView.MINIMAL)
   @Field
   @Lob
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   @Description
   private String description;

   public TsDiskFolder() {
   }

   public TsDiskFolder(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getDescription() {
      return description;
   }

   @Override
   public boolean isFolder() {
      return true;
   }
}
