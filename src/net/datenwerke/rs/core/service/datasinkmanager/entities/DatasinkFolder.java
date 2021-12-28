package net.datenwerke.rs.core.service.datasinkmanager.entities;

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
import net.datenwerke.gxtdto.client.dtomanager.FolderDto;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.service.datasinkmanager.locale.DatasinkManagerMessages;
import net.datenwerke.rs.utils.instancedescription.annotations.Description;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;
import net.datenwerke.rs.utils.instancedescription.annotations.Title;
import net.datenwerke.treedb.service.treedb.annotation.TreeDBAllowedChildren;

/**
 * Entity that can be used to organize datasinks hierarchically.
 * 
 *
 */
@Entity
@Table(name = "DATASINK_FOLDER")
@Audited
@Indexed
@TreeDBAllowedChildren({ DatasinkFolder.class, DatasinkDefinition.class })
@GenerateDto(dtoPackage = "net.datenwerke.rs.core.client.datasinkmanager.dto", dtoImplementInterfaces = FolderDto.class, typeDescriptionMsg = BaseMessages.class, typeDescriptionKey = "folder", icon = "folder")
@InstanceDescription(msgLocation = DatasinkManagerMessages.class, objNameKey = "datasinkFolderTypeName", icon = "folder")
public class DatasinkFolder extends AbstractDatasinkManagerNode {

   /**
    * 
    */
   private static final long serialVersionUID = 3139118358223220435L;

   @ExposeToClient(displayTitle = true)
   @Column(length = 128)
   @Title
   @Field
   private String name;

   @ExposeToClient(view = DtoView.MINIMAL)
   @Lob
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   @Description
   @Field
   private String description;

   public DatasinkFolder() {

   }

   public DatasinkFolder(String folder) {
      setName(folder);
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
